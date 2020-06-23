package com.itmacy.dev.module.miniprogram.constant;

/**
 * 小程序常量文件
 * Created by itmacy on 2019/8/1.
 */
public class MiniProgramConstant {


    /**
     * appId
     */
    public static final String APP_ID = "your appId";

    /**
     * secret
     */
    public static final String SECRET = "your secret";


    /**
     * 获取accessToken路径
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


    /**
     * code换取session的路径
     */
    public static final String CODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
}
