package com.itmacy.dev.module.officialaccount.service;


import com.itmacy.dev.module.officialaccount.model.WeChatUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 核心服务
 * Created by Administrator on 2016/11/8.
 */
public interface CoreService {

    /**
     * 处理请求
     * @param request
     * @return
     */
    String processRequest(HttpServletRequest request) ;


    /**
     * 根据openId获取用户信息
     * @param openId
     * @return
     */
    WeChatUserInfo getUserInfoByOpenId(String openId);


    /**
     * 判断用户是否已关注公众号
     * @return
     */
    Boolean isUserSubscribe(String openId);

}
