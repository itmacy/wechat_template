package com.itmacy.dev.module.officialaccount;

import com.itmacy.dev.module.officialaccount.constant.WeChatConstant;
import com.itmacy.dev.module.officialaccount.model.AccessToken;
import com.itmacy.dev.module.officialaccount.utils.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * 定时获取微信access_token的线程
 *在WechatMpDemoApplication中注解@EnableScheduling，在程序启动时就开启定时任务。
 * 每7200秒执行一次
 */
@Component
public class AccessTokenThread {

    private static Logger log = LoggerFactory.getLogger(AccessTokenThread.class);
    @Resource
    private RedisTemplate redisTemplate;


    @Scheduled(fixedRate = 5400*1000,initialDelay=0)
    //5400秒执行一次
    public String getRefreshToken(){

        String token = getWeChatAccessToken();

        if (StringUtils.isEmpty(token)){
            log.error("获取token失败,重试3次");

            //重试3次
            int count = 0;
            while (count < 3){
                count ++;
                log.error("第{}次获取token",count);

                token = getWeChatAccessToken();
                if (!StringUtils.isEmpty(token)){
                    return token;
                }
            }
            return null;
        }
        return token;
    }


    /**
     * 获取微信acessToken
     * @return
     */
    @SuppressWarnings("unchecked")
    private String getWeChatAccessToken(){
        AccessToken accessToken= WeixinUtil.getAccessToken(WeChatConstant.APP_ID,WeChatConstant.APP_SECRET);

        if(null != accessToken && !StringUtils.isEmpty(accessToken.getToken())){
            //存入redis
            redisTemplate.opsForValue().set(WeChatConstant.WECHAT_ACCESS_TOKEN,accessToken.getToken(),2, TimeUnit.HOURS);

            return accessToken.getToken();
        }

        return null;
    }



    /**
     * 获取accessToken
     * @return
     */
    public String getAccessToken(){

        //从redis中获取
        String accessToken = (String)redisTemplate.opsForValue().get(WeChatConstant.WECHAT_ACCESS_TOKEN);

        if (StringUtils.isEmpty(accessToken)){

            int count = 0;
            while (count < 3){
                count ++;

                accessToken = getWeChatAccessToken();

                if (!StringUtils.isEmpty(accessToken)){
                    return accessToken;
                }

            }
        }

        return accessToken;

    }

}
