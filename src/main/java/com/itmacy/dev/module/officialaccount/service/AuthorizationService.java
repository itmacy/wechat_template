package com.itmacy.dev.module.officialaccount.service;

import com.itmacy.dev.module.officialaccount.model.AuthorizationToken;
import com.itmacy.dev.module.officialaccount.model.AuthorizationUrl;

/**
 * 网页授权服务
 * Created by itmacy on 2017/11/13.
 */
public interface AuthorizationService {

    /**
     * 获取授权url
     * @return
     */
    AuthorizationUrl getAuthorizationUrl();

    /**
     * 获取授权url
     * @param redirectUrl 重定向地址
     * @return
     */
    AuthorizationUrl getAuthorizationUrl(String redirectUrl);

    /**
     * 根据code获取openId
     * @param code
     * @return
     */
    AuthorizationToken getAuthorizationTokenByCode(String code);
}
