package com.itmacy.dev.module.wechatlogin.dto;

import lombok.Data;

/**
 * accessToken响应
 * Created by itmacy on 2019/8/7.
 */
@Data
public class AccessToken {


    /**
     * {
     "access_token":"ACCESS_TOKEN",
     "expires_in":7200,
     "refresh_token":"REFRESH_TOKEN","openid":"OPENID",
     "scope":"SCOPE"
     }
     */
    /**
     * {
     "access_token":"ACCESS_TOKEN",
     "expires_in":7200,
     "refresh_token":"REFRESH_TOKEN",
     "openid":"OPENID",
     "scope":"SCOPE",
     "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     }
     */

    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;
}
