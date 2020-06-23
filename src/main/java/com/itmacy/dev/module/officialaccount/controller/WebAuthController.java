package com.itmacy.dev.module.officialaccount.controller;

import com.itmacy.dev.base.response.GlobalEnum;
import com.itmacy.dev.base.response.ResponseData;
import com.itmacy.dev.base.response.ResponseDataUtil;
import com.itmacy.dev.module.officialaccount.model.AuthorizationToken;
import com.itmacy.dev.module.officialaccount.model.AuthorizationUrl;
import com.itmacy.dev.module.officialaccount.model.RedirectUrl;
import com.itmacy.dev.module.officialaccount.service.AuthorizationService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 网页授权控制器
 *
 * 流程说明：
 * 1.网页端通过调用/getAuthorizationUrl获取授权url
 * 2.当用户同意授权时，就重定向到指定的url,此时的url中带有参数code
 * 3.网页端通过参数code，调用/getOpenId/{code}，从而获取openId
 *
 * @Author:itmacy
 * @Date:2020/6/22
 */
@RestController
@RequestMapping("/webAuth")
public class WebAuthController extends BaseController {

    @Resource
    private AuthorizationService authorizationService;

    /**
     * 获取授权url
     * @return
     */
    @GetMapping(value = "/getAuthorizationUrl",name = "获取授权url")
    @SuppressWarnings("unchecked")
    public ResponseData<AuthorizationUrl> getAuthorizationUrl(){

        AuthorizationUrl authorizationUrl = authorizationService.getAuthorizationUrl();

        if (authorizationUrl != null){
            return ResponseDataUtil.response(GlobalEnum.success,authorizationUrl);
        }

        return ResponseDataUtil.response(GlobalEnum.query_fail);

    }

    /**
     * 获取授权url
     * @return
     */
    @PostMapping(value = "/getAuthUrl",name = "获取授权url")
    @SuppressWarnings("unchecked")
    public ResponseData<AuthorizationUrl> getAuthUrl(@RequestBody RedirectUrl redirectUrl){

        if (redirectUrl == null || StringUtils.isEmpty( redirectUrl.getRedirectUrl())){
            return ResponseDataUtil.response(GlobalEnum.parameter_error);
        }

        AuthorizationUrl authorizationUrl = authorizationService.getAuthorizationUrl();

        if (authorizationUrl != null){
            return ResponseDataUtil.response(GlobalEnum.success,authorizationUrl);
        }

        return ResponseDataUtil.response(GlobalEnum.query_fail);

    }


    /**
     * 根据code获取access_token
     * @param code
     * @return
     */
    //code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
    @GetMapping(value = "/getOpenId/{code}",name = "根据code获取access_token")
    @SuppressWarnings("unchecked")
    public ResponseData<AuthorizationToken> getOpenId(@PathVariable String code){
        logger.info("code:"+code);

        AuthorizationToken token = authorizationService.getAuthorizationTokenByCode(code);

        if (token == null){
            return ResponseDataUtil.response(GlobalEnum.query_fail);
        }

        return ResponseDataUtil.response(GlobalEnum.success,token);

    }


    // 根据accessToken和openId获取用户信息


}
