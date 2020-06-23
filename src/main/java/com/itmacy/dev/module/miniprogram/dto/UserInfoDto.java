package com.itmacy.dev.module.miniprogram.dto;

import lombok.Data;

/**
 * 用户信息
 * Created by itmacy on 2019/8/27.
 */
@Data
public class UserInfoDto {

    /**
     * {
     "openId":"oukgC5ZiWKw9GMU2ixZ2Ja4huWwg",
     "nickName":"Raise me up",
     "gender":1,
     "language":"zh_CN",
     "city":"Guangzhou",
     "province":"Guangdong",
     "country":"China",
     "avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEImvQ4191uHVutibrNgRmylOnMicd6LXPLpNAMegics6Od6Azic7oSnHO7V4gKW6G75mNjzkQHCO1BicLw/132",
     "unionId":"oBxilv0AyfFt3GL5B1QCNJjX5lKY",
     "watermark":{
     "timestamp":1566878556,
     "appid":"wx81dc3bd894aff7f0"
     }
     }
     */

    private String openId;
    private String nickName;
    private String gender;
    private String language;
    private String city;
    private String province;
    private String avatarUrl;
    private String unionId;
    private Watermark watermark;


    @Data
    public static class Watermark{
        private String timestamp;
        private String appid;
    }
}
