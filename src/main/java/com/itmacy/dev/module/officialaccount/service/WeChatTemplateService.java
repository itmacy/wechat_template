package com.itmacy.dev.module.officialaccount.service;


import com.itmacy.dev.module.officialaccount.template.req.SendTemplate;

/**
 * 模板服务
 * Created by itmacy on 2019/7/31.
 */
public interface WeChatTemplateService {


    /**
     * 发送模板消息
     * @param sendTemplateBean
     * @return
     */
    boolean sendTemplateMessage(SendTemplate sendTemplateBean);




}
