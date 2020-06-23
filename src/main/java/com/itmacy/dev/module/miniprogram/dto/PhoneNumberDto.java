package com.itmacy.dev.module.miniprogram.dto;

import lombok.Data;

/**
 * 手机号dto
 * Created by itmacy on 2019/8/27.
 */
@Data
public class PhoneNumberDto {

    /**
     * {
     "phoneNumber": "13580006666",
     "purePhoneNumber": "13580006666",
     "countryCode": "86",
     "watermark":
     {
     "appid":"APPID",
     "timestamp": TIMESTAMP
     }
     }
     */

    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private UserInfoDto.Watermark watermark;
}
