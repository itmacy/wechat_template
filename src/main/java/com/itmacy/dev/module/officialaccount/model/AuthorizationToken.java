package com.itmacy.dev.module.officialaccount.model;

import lombok.Data;

/**
 * 网页授权token
 * Created by itmacy on 2017/11/13.
 */
@Data
public class AuthorizationToken {

    //    {
//        "access_token":"ACCESS_TOKEN",
//            "expires_in":7200,
//            "refresh_token":"REFRESH_TOKEN",
//            "openid":"OPENID",
//            "scope":"SCOPE"
//    }

    private String access_token;// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
    private int expires_in;// access_token接口调用凭证超时时间，单位（秒）
    private String refresh_token; // 用户刷新access_token
    private String openid; // 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
    private String scope; // 用户授权的作用域，使用逗号（,）分隔
}
