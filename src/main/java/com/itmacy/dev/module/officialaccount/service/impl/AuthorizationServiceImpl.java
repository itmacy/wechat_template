package com.itmacy.dev.module.officialaccount.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itmacy.dev.module.officialaccount.constant.WeChatConstant;
import com.itmacy.dev.module.officialaccount.model.AuthorizationToken;
import com.itmacy.dev.module.officialaccount.model.AuthorizationUrl;
import com.itmacy.dev.module.officialaccount.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 网页授权服务实现
 * Created by itmacy on 2017/11/13.
 */
@Slf4j
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Resource
    private RestTemplate restTemplate;

    @Value(value = "${self-config.wechat.redirect}")
    private String redirect;

    /**
     * 获取授权url
     * @return
     */
    @Override
    public AuthorizationUrl getAuthorizationUrl(){

        try {

            AuthorizationUrl url = new AuthorizationUrl();
            //对跳转的url进行编码
            String redirectUrl = URLEncoder.encode(redirect,"UTF-8");
            //授权url
            String authorizeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
                    WeChatConstant.APP_ID +
                    "&redirect_uri="+redirectUrl+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

            url.setAuthorizeUrl(authorizeUrl);

            return url;

        } catch (UnsupportedEncodingException e) {
            log.error("获取授权url失败");
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String redirectUrl = URLEncoder.encode("https://smarttoilet.isaigu.com/","UTF-8");
        System.out.println(redirectUrl);
    }

    /**
     * 获取授权url
     * @param redirectUrl 重定向地址
     * @return
     */
    @Override
    public AuthorizationUrl getAuthorizationUrl(String redirectUrl) {

        if (StringUtils.isEmpty(redirectUrl)){
            return null;
        }
        try {

            AuthorizationUrl url = new AuthorizationUrl();
            //对跳转的url进行编码
            String encodeRedirectUrl = URLEncoder.encode(redirectUrl,"UTF-8");
            //授权url
            String authorizeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
                    WeChatConstant.APP_ID +
                    "&redirect_uri="+encodeRedirectUrl+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

            url.setAuthorizeUrl(authorizeUrl);

            return url;

        } catch (UnsupportedEncodingException e) {
            log.error("获取授权url失败");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据code获取网页授权token,openId
     * @param code
     * @return
     */
    @Override
    public AuthorizationToken getAuthorizationTokenByCode(String code) {

        if (org.springframework.util.StringUtils.isEmpty(code)){
            return null;
        }
        AuthorizationToken authorizationToken = new AuthorizationToken();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                WeChatConstant.APP_ID +
                "&secret=" +
                WeChatConstant.APP_SECRET +
                "&code=CODE&grant_type=authorization_code";

        String accessTokenUrl = url.replace("CODE", code);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(accessTokenUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            String body = responseEntity.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            log.info("errcode:"+jsonObject.getString("errcode"));
            log.info("errmsg:"+jsonObject.getString("errmsg"));

//            if (jsonObject.getString("errcode").equals("0")){
                authorizationToken = JSON.parseObject(body, AuthorizationToken.class);
                System.out.println("==================="+authorizationToken);
//            }
        }

        return authorizationToken;
    }
}
