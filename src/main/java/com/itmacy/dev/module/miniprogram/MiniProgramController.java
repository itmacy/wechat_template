package com.itmacy.dev.module.miniprogram;

import com.itmacy.dev.base.response.GlobalEnum;
import com.itmacy.dev.base.response.ResponseData;
import com.itmacy.dev.base.response.ResponseDataUtil;
import com.itmacy.dev.module.miniprogram.dto.Code2SessionResponse;
import com.itmacy.dev.module.miniprogram.dto.PhoneNumberDto;
import com.itmacy.dev.module.miniprogram.dto.UserInfoDto;
import com.itmacy.dev.module.miniprogram.dto.req.GetDecryptDataReq;
import com.itmacy.dev.module.miniprogram.service.MiniProgramService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 小程序控制器
 *
 * 流程说明：
 * 1.小程序端通过调用login API 从而获取code
 * 2.小程序端通过调用/getCode2Session.json/{code} 从而获取openId和session_key等
 * 3.小程序获取用户加密信息或电话号码时，需要调用对应的接口进行解密
 *
 * Created by itmacy on 2019/7/30.
 */
@RestController
@RequestMapping("/miniProgram")
public class MiniProgramController {

    @Resource
    private MiniProgramService miniProgramService;


    /**
     * code2Session，用于获取小程序用户openId
     * @param code
     * @return
     */
    @GetMapping(value = "/getCode2Session.json/{code}",name = "code2Session")
    @SuppressWarnings("unchecked")
    public ResponseData<Code2SessionResponse> getCode2Session(@PathVariable("code") String code){

        Code2SessionResponse code2Session = miniProgramService.getCode2Session(code);

        return ResponseDataUtil.response(GlobalEnum.success,code2Session);
    }


    /**
     * 获取解密用户信息
     * @param req
     * @return
     */
    @PostMapping(value = "/getDecryptUserInfo.json",name = "获取解密后的用户信息")
    @SuppressWarnings("unchecked")
    public  ResponseData<UserInfoDto> getDecryptUserInfo(@RequestBody GetDecryptDataReq req){


        UserInfoDto userInfoDto = miniProgramService.getDecryptUserInfo(req.getSession_key(), req.getIv(), req.getEncryptData());

        if (userInfoDto != null){
            return ResponseDataUtil.response(GlobalEnum.success,userInfoDto);
        }

        return ResponseDataUtil.response(GlobalEnum.query_fail);

    }



    /**
     * 获取解密手机号
     * @param req
     * @return
     */
    @PostMapping(value = "/getDecryptPhoneNumber.json",name = "获取解密后的电话号码")
    @SuppressWarnings("unchecked")
    public  ResponseData<PhoneNumberDto> getDecryptPhoneNumber(@RequestBody GetDecryptDataReq req){


        PhoneNumberDto decryptPhoneNumber = miniProgramService.getDecryptPhoneNumber(req.getSession_key(), req.getIv(), req.getEncryptData());

        if (decryptPhoneNumber != null){
            return ResponseDataUtil.response(GlobalEnum.success,decryptPhoneNumber);
        }

        return ResponseDataUtil.response(GlobalEnum.query_fail);

    }


}
