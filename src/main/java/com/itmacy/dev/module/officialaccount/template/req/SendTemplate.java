package com.itmacy.dev.module.officialaccount.template.req;

import lombok.Data;

/**
 * 发送模板消息
 * Created by itmacy on 2019/7/31.
 */
@Data
public class SendTemplate {

    /**
     *  {
     "touser":"OPENID",
     "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
     "url":"http://weixin.qq.com/download",
     "miniprogram":{
     "appid":"xiaochengxuappid12345",
     "pagepath":"index?foo=bar"
     },
     "data":{
     "first": {
     "value":"恭喜你购买成功！",
     "color":"#173177"
     },
     "keyword1":{
     "value":"巧克力",
     "color":"#173177"
     },
     "keyword2": {
     "value":"39.8元",
     "color":"#173177"
     },
     "keyword3": {
     "value":"2014年9月22日",
     "color":"#173177"
     },
     "remark":{
     "value":"欢迎再次购买！",
     "color":"#173177"
     }
     }
     }
     */
    private String touser;//openId
    private String template_id;//模板id
    private String url;//跳转url
    private MiniProgram miniProgram;//跳转小程序
    private TemplateData data;



    @Data
    public static class MiniProgram{

        private String appid;
        private String pagepath;

    }
}
