package com.itmacy.dev.module.wechatlogin.constant;

/**
 * 微信登录常量文件
 * Created by itmacy on 2019/8/7.
 */
public class WeChatLoginConstant {


    /**
     * appId
     */
    public static final String APP_ID = "your appId";


    /**
     * secret
     */
    public static final String APP_SECRET = "your secret";



    /**
     * 请求CODE
     */
    public static final String GET_CODE = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";


    /**
     * 获取accessToken
     */
    public static final String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";


    /**
     * 刷新accessToken
     */
    public static final String REFRESH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    /**
     * 获取用户信息
     */
    public static final String GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";


}
