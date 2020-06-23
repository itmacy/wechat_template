package com.itmacy.dev.module.officialaccount.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itmacy.dev.module.officialaccount.AccessTokenThread;
import com.itmacy.dev.module.officialaccount.constant.WeChatConstant;
import com.itmacy.dev.module.officialaccount.service.WeChatTemplateService;
import com.itmacy.dev.module.officialaccount.template.req.SendTemplate;
import com.itmacy.dev.module.officialaccount.utils.WeixinUtil;
import com.itmacy.dev.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 微信模板服务实现
 * Created by itmacy on 2019/8/1.
 */
@Service
@Slf4j
public class WeChatTemplateServiceImpl implements WeChatTemplateService {

    @Resource
    private AccessTokenThread accessTokenThread;


    /**
     * 发送模板信息
     * @param sendTemplateBean
     * @return
     */
    @Override
    public boolean sendTemplateMessage(SendTemplate sendTemplateBean) {


        String accessToken = accessTokenThread.getAccessToken();
        if (accessToken == null){
            return false;
        }

        String url = WeChatConstant.TEMPLATE_SEND_MESSAGE.replace("ACCESS_TOKEN", accessToken);

        // 转换成json字符串
        String jsonString = JSON.toJSONString(sendTemplateBean);

        log.info("请求url:{}",url);
        log.info("请求参数：{}",jsonString);

        //发送模板消息
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonString);

        if (null != jsonObject && 0 == jsonObject.getIntValue("errcode")) {

            log.info("发送微信模板消息成功：{}",jsonString);

            return true;
        }

        log.error("发送模板信息失败：{}", JsonUtil.prettyPrintJsonString(jsonObject));

        //重试3次
        int count = 0;

        while (count < 3 ){
            count ++ ;

            log.error("重试发送模板消息第{}次",count);
            //发送模板消息
            jsonObject = WeixinUtil.httpRequest(url, "POST", jsonString);

            if (null != jsonObject && 0 == jsonObject.getIntValue("errcode")) {

                log.info("发送微信模板消息成功：{}",jsonString);

                return true;
            }
        }
        return false;
    }



}
