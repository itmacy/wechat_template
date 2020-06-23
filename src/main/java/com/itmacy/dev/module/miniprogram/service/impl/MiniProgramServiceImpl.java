package com.itmacy.dev.module.miniprogram.service.impl;

import com.alibaba.fastjson.JSON;
import com.itmacy.dev.module.miniprogram.constant.MiniProgramConstant;
import com.itmacy.dev.module.miniprogram.dto.Code2SessionResponse;
import com.itmacy.dev.module.miniprogram.dto.PhoneNumberDto;
import com.itmacy.dev.module.miniprogram.dto.UserInfoDto;
import com.itmacy.dev.module.miniprogram.service.MiniProgramService;
import com.itmacy.dev.module.miniprogram.utils.DecryptUtil;
import com.itmacy.dev.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 小程序服务实现
 * Created by itmacy on 2019/7/30.
 */
@Service
@Slf4j
public class MiniProgramServiceImpl implements MiniProgramService {


    @Resource
    private RestTemplate restTemplate;


    /**
     * 根据code获取登录凭证
     *
     * @param code
     * @return
     */
    @Override
    public Code2SessionResponse getCode2Session(String code) {

        String url = MiniProgramConstant.CODE_2_SESSION.
                replace("APPID",MiniProgramConstant.APP_ID).
                replace("SECRET",MiniProgramConstant.SECRET).
                replace("JSCODE",code);

        log.info("code换取登录凭证url:{}",url);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {

            String body = responseEntity.getBody();
//            {"session_key":"eTM1MZff3Fb3f\/NrQAGLAQ==","openid":"oukgC5ZiWKw9GMU2ixZ2Ja4huWwg"}
            log.info("返回参数：{}",body);

            Code2SessionResponse response = JSON.parseObject(body, Code2SessionResponse.class);

            if (response.getErrcode() != null){
                log.error("调用getCode2Session请求失败：{}", JsonUtil.prettyPrintJsonString(response));
            }

            return response;
        }

        return null;
    }



    /**
     * 解密获取小程序用户信息
     * @param session_key
     * @param iv
     * @param encryptData
     * @return
     */
    @Override
    public UserInfoDto getDecryptUserInfo(String session_key, String iv, String encryptData) {
        UserInfoDto userInfoDto = null;

        String decrypt = DecryptUtil.decrypt(session_key, iv, encryptData);

        if (!StringUtils.isEmpty(decrypt)){
            userInfoDto = JSON.parseObject(decrypt, UserInfoDto.class);
        }

        return userInfoDto;
    }


    /**
     * 解密获取小程序手机号
     * @param session_key
     * @param iv
     * @param encryptData
     * @return
     */
    @Override
    public PhoneNumberDto getDecryptPhoneNumber(String session_key, String iv, String encryptData) {
        PhoneNumberDto phoneNumberDto = null;

        String decrypt = DecryptUtil.decrypt(session_key, iv, encryptData);

        if (!StringUtils.isEmpty(decrypt)){
            phoneNumberDto = JSON.parseObject(decrypt, PhoneNumberDto.class);
        }

        return phoneNumberDto;
    }

}
