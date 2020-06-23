package com.itmacy.dev.module.wechatlogin;


import com.itmacy.dev.base.response.GlobalEnum;
import com.itmacy.dev.base.response.ResponseData;
import com.itmacy.dev.base.response.ResponseDataUtil;
import com.itmacy.dev.module.wechatlogin.dto.AccessToken;
import com.itmacy.dev.module.wechatlogin.dto.UserInfo;
import com.itmacy.dev.module.wechatlogin.req.GetUserInfoReq;
import com.itmacy.dev.module.wechatlogin.service.WeChatLoginService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信登录控制器
 * 参考官网：https://developers.weixin.qq.com/doc/oplatform/Website_App/WeChat_Login/Wechat_Login.html
 *
 *
 * 流程说明：
 * 1.前端使用微信提供的sdk在网页生成二维码
 * 2.当用户使用微信扫一扫二维码后，会重定向到指定的url,此时的url带有微信返回的code
 * 3.前端调用/getAccessTokenByCode.json/{code}接口，从而获取access_token和openId等
 * 4.通过access_token和openId可以调用获取用户信息
 *
 * Created by itmacy on 2019/8/8.
 */
@RestController
@RequestMapping("/weChatLogin")
public class WeChatLoginController {


    @Resource
    private WeChatLoginService weChatLoginService;

    /**
     * 获取accessToken
     * @return
     */
    @GetMapping(value = "/getAccessTokenByCode.json/{code}")
    @SuppressWarnings("unchecked")
    public ResponseData<AccessToken> getAccessTokenByCode(@PathVariable("code") String code){


        if (StringUtils.isEmpty(code)){
            return ResponseDataUtil.response(GlobalEnum.parameter_error);
        }

        AccessToken accessToken = weChatLoginService.getAccessTokenByCode(code);

        if (accessToken == null){
            return ResponseDataUtil.response(GlobalEnum.query_fail);
        }

        return ResponseDataUtil.response(GlobalEnum.success,accessToken);
    }


    /**
     * 获取用户信息
     * @return
     */
    @PostMapping(value = "/getWechatUserInfo.json")
    @SuppressWarnings("unchecked")
    public ResponseData<UserInfo> getWechatUserInfo(@RequestBody GetUserInfoReq req){


        if (req == null ||StringUtils.isEmpty(req.getAccessToken()) || StringUtils.isEmpty(req.getOpenId())){
            return ResponseDataUtil.response(GlobalEnum.parameter_error);
        }

        UserInfo userInfo = weChatLoginService.getUserInfo(req.getAccessToken(), req.getOpenId());

        if (userInfo == null){
            return ResponseDataUtil.response(GlobalEnum.query_fail);
        }

        return ResponseDataUtil.response(GlobalEnum.success,userInfo);
    }

}
