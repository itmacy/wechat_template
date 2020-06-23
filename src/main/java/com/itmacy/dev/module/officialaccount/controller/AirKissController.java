package com.itmacy.dev.module.officialaccount.controller;


import com.itmacy.dev.base.response.GlobalEnum;
import com.itmacy.dev.base.response.ResponseData;
import com.itmacy.dev.base.response.ResponseDataUtil;
import com.itmacy.dev.module.officialaccount.model.AirKissConfig;
import com.itmacy.dev.module.officialaccount.model.AirKissConfigReq;
import com.itmacy.dev.module.officialaccount.service.AirKissService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * airKiss控制器
 * 参考官网：https://developers.weixin.qq.com/doc/offiaccount/Connecting_devices/Introduction_to_Features.html
 *
 * 流程说明：
 * 1.前端调用/getAirKissConfig从而获取airkiss等相关配置
 * 2.前端获取到配置后，再调用微信提供的api进行对设备的WiFi配置
 *
 * Created by itmacy on 2019/9/25.
 */
@RestController
@RequestMapping("/airKiss")
public class AirKissController {


    @Resource
    private AirKissService airKissService;


    /**
     * 获取airKiss配置
     * @param req
     * @return
     */
    @PostMapping(value = "/getAirKissConfig",name = "获取airKiss配置")
    @SuppressWarnings("unchecked")
    public ResponseData<AirKissConfig> getAirKissConfig(@RequestBody AirKissConfigReq req){

        if (StringUtils.isEmpty(req.getHtmlUrl())){
            return ResponseDataUtil.response(GlobalEnum.parameter_error);
        }

        AirKissConfig airKissConfig = airKissService.getAirKissConfig(req.getHtmlUrl());

        if (airKissConfig != null){
            return ResponseDataUtil.response(GlobalEnum.success,airKissConfig);
        }

        return ResponseDataUtil.response(GlobalEnum.query_fail);

    }

}
