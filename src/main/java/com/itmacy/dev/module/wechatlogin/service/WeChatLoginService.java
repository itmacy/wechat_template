package com.itmacy.dev.module.wechatlogin.service;


import com.itmacy.dev.module.wechatlogin.dto.AccessToken;
import com.itmacy.dev.module.wechatlogin.dto.UserInfo;

/**
 * 核心服务
 * Created by itmacy on 2019/8/7.
 */
public interface WeChatLoginService {


    /**
     * 根据code获取accessToken
     * @param code
     * @return
     */
    AccessToken getAccessTokenByCode(String code);


    /**
     * 获取用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    UserInfo getUserInfo(String accessToken, String openId);



}
