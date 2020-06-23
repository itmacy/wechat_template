package com.itmacy.dev.module.officialaccount.constant;

/**
 * 公众号常量文件
 * Created by itmacy on 2019/7/31.
 */
public class WeChatConstant {

    /**
     * appId 第三方用户唯一凭证
     */
    public static final String APP_ID = "your appId";

    /**
     * secret 第三方用户唯一凭证密钥
     */
    public static final String APP_SECRET = "your secret";

    /**
     * Token，在微信开发者平台定义的
     */
    public static final String TOKEN = "dfdflekrelkflkfeFDKFSDK55Ffdsf5f";

    /**
     * EncodingAESKey,开发者手动填写或随机生成，将用作消息体加解密密钥
     */
    public static final String EncodingAESKey = "L3DX9QUVvs7ds31psyOIfsY0dF2YrcJsseZUsUVlkwN";


    /**
     * 获取accessToken路径
     * 获取access_token的接口地址（GET） 限200（次/天）
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 菜单模板路径
     */
    public static final String MENU_TEMPLATE_URL = "wechat/WeChatMenuTemplate.json";

    /**
     * 微信accessToken
     */
    public static final String WECHAT_ACCESS_TOKEN = "wechat_access_token";


    /**
     * jsapi ticket url
     */
    public static final String JS_API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=ACCESS_TOKEN";

    /**
     * jsapi_ticket
     */
    public static final String WECHAT_JS_AIP_TICKET = "wechat_jsapi_ticket";

    /******************************模板id*******************************/

    //设备告警通知
    public static final String TEMPLATE_ID_DEVICE_WARN = "3UsOrvqJIjz6Vq5G2cJyX-HoPPpJV7RRZEa9p7AYTu0";



    /******************************用户*******************************/

    /**
     * 获取用户基本信息
     */
    public static final String GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";


    /******************************菜单*******************************/


    // 菜单创建（POST） 限1000（次/天）
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 菜单查询（POST） 限10000（次/天）
    public static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    // 菜单删除（POST） 限1000（次/天）
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";



    /******************************模板*******************************/


    //获取模板列表
    public static final String TEMPLATE_GET_LIST = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";

    //删除模板
    public static final String TEMPLATE_DELETE = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";

    //发送模板消息
    public static final String TEMPLATE_SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

}
