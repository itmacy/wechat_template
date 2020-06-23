package com.itmacy.dev.module.wechatlogin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.itmacy.dev.module.wechatlogin.constant.WeChatLoginConstant;
import com.itmacy.dev.module.wechatlogin.dto.AccessToken;
import com.itmacy.dev.module.wechatlogin.dto.UserInfo;
import com.itmacy.dev.module.wechatlogin.service.WeChatLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 核心服务实现
 * Created by itmacy on 2019/8/7.
 */
@Service
@Slf4j
public class WeChatLoginServiceImpl implements WeChatLoginService {

    @Resource
    private RestTemplate restTemplate;


    /**
     * 获取accessToken
     * @param code
     * @return
     */
    @Override
    public AccessToken getAccessTokenByCode(String code) {

        if (StringUtils.isEmpty(code)){
            log.error("code为空");
            return null;
        }

        String url = WeChatLoginConstant.GET_ACCESS_TOKEN.
                replace("APPID", WeChatLoginConstant.APP_ID).
                replace("SECRET", WeChatLoginConstant.APP_SECRET).
                replace("CODE",code);


        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);


        if (response.getStatusCode().is2xxSuccessful()){

            JSONObject jsonObject = JSON.parseObject(response.getBody());

            if (jsonObject.getInteger("errcode") != null && jsonObject.getInteger("errcode") != 0){

                log.error("获取accessToken失败：errcode:{},errmsg:{}",jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
                return null;
            }


            return JSON.parseObject(response.getBody(), AccessToken.class);

        }


        return null;
    }


    /**
     * 获取用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    @Override
    public UserInfo getUserInfo(String accessToken, String openId) {

        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(openId)){

            log.error("accssToken:{},openId:{}",accessToken,openId);
            return null;
        }


        String url = WeChatLoginConstant.GET_USER_INFO.
                replace("ACCESS_TOKEN",accessToken).
                replace("OPENID",openId);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()){

            JSONObject jsonObject = JSON.parseObject(response.getBody());

            if (jsonObject.getInteger("errcode") != null && jsonObject.getInteger("errcode") != 0){

                log.error("获取用户信息失败：errcode:{},errmsg:{}",jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
                return null;
            }

            return JSON.parseObject(response.getBody(), UserInfo.class);
        }

        return null;
    }
}
