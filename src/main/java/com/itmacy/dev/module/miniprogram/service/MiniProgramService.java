package com.itmacy.dev.module.miniprogram.service;


import com.itmacy.dev.module.miniprogram.dto.Code2SessionResponse;
import com.itmacy.dev.module.miniprogram.dto.PhoneNumberDto;
import com.itmacy.dev.module.miniprogram.dto.UserInfoDto;

/**
 * 小程序服务
 * Created by itmacy on 2019/7/30.
 */
public interface MiniProgramService {

    @interface GetDecryptDta{}

    /**
     * 通过code获取登录凭证
     * @param code
     * @return
     */
    Code2SessionResponse getCode2Session(String code);


    /**
     * 解密获取用户信息
     * @param session_key
     * @param iv
     * @param encryptData
     * @return
     */
    UserInfoDto getDecryptUserInfo(String session_key, String iv, String encryptData);


    /**
     * 解密获取用户手机号
     * @param session_key
     * @param iv
     * @param encryptData
     * @return
     */
    PhoneNumberDto getDecryptPhoneNumber(String session_key, String iv, String encryptData);


}
