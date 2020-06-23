package com.itmacy.dev.module.officialaccount.service.impl;

import com.alibaba.fastjson.JSON;
import com.itmacy.dev.module.officialaccount.AccessTokenThread;
import com.itmacy.dev.module.officialaccount.constant.WeChatConstant;
import com.itmacy.dev.module.officialaccount.model.AirKissConfig;
import com.itmacy.dev.module.officialaccount.service.AirKissService;
import com.itmacy.dev.module.officialaccount.utils.SHA1;
import com.itmacy.dev.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * airKiss服务实现
 * Created by itmacy on 2019/9/25.
 */
@Service
@Slf4j
public class AirKissServiceImpl implements AirKissService {

    @Resource
    private AccessTokenThread accessTokenThread;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public AirKissConfig getAirKissConfig(String htmlUrl) {

        try {

            String jsapi_ticket = getJsTicket();

            if (StringUtils.isEmpty(jsapi_ticket)){
                log.error("获取jsTicket失败");
                return null;
            }

            String nonce_str = UUID.randomUUID().toString();
            String timestamp = Long.toString(System.currentTimeMillis() / 1000);

            // 注意这里参数名必须全部小写，且必须有序
            String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + htmlUrl;
            System.out.println(string1);

            String signature = SHA1.gen(string1);

            AirKissConfig config = new AirKissConfig();
            config.setAppId(WeChatConstant.APP_ID);
            config.setDebug(true);
            config.setLink(htmlUrl);
            config.setNonce(nonce_str);
            config.setSignature(signature);
            config.setTimestamp(timestamp);
            return config;

        } catch (NoSuchAlgorithmException e) {
            log.error("获取airkiss配置失败：{}",e.getMessage());
            e.printStackTrace();
        }

        return null;

    }


    @SuppressWarnings("unchecked")
    private String getJsTicket() {

        //从redis中获取
        String ticket = (String)redisTemplate.opsForValue().get(WeChatConstant.WECHAT_JS_AIP_TICKET);

        if (StringUtils.isEmpty(ticket)){

            int count = 0;
            while (count < 3){
                count ++;
                ticket = getTicket();

                if (!StringUtils.isEmpty(ticket)){
                    redisTemplate.opsForValue().set(WeChatConstant.WECHAT_JS_AIP_TICKET,ticket,6000, TimeUnit.SECONDS);
                    return ticket;
                }

            }
        }
        return ticket;

    }


    private String getTicket() {
        String ticket = getTicket1();

        if (!StringUtils.isEmpty(ticket)){
           return ticket;
        }
        // 再获取一次
        return getTicket1();
    }



    private String getTicket1(){

        String accessToken = accessTokenThread.getAccessToken();

        if (StringUtils.isEmpty(accessToken)){
            return null;
        }

        ResponseEntity<String> response = restTemplate.getForEntity(WeChatConstant.JS_API_TICKET_URL.replace("ACCESS_TOKEN",accessToken), String.class);

        if (response.getStatusCode().is2xxSuccessful()){
            String body = response.getBody();
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(body);

            log.error("获取ticket：{}", JsonUtil.prettyPrintJsonString(body));

            Integer errcode = jsonObject.getInteger("errcode");
            if (errcode == null || errcode == 0){

                return jsonObject.getString("ticket");
            }

            // {"errcode":40001,"errmsg":"invalid credential, access_token is invalid or not latest hints: [QimAve4MRa-lR!]"}
            if (errcode == 40001){
                // 重新获取accessToken，
                accessTokenThread.getRefreshToken();
            }

        }

        log.error("获取ticket失败：{}", JsonUtil.prettyPrintJsonString(response));

        return null;
    }


    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);

    }
}
