package com.itmacy.dev.module.officialaccount.controller;

import com.itmacy.dev.base.response.GlobalEnum;
import com.itmacy.dev.base.response.ResponseData;
import com.itmacy.dev.base.response.ResponseDataUtil;
import com.itmacy.dev.module.officialaccount.service.WeChatTemplateService;
import com.itmacy.dev.module.officialaccount.template.req.SendTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信模板控制器
 * Created by itmacy on 2019/8/1.
 */
@RestController
@RequestMapping("/weChatTemplate")
public class WeChatTemplateController {

    @Resource
    private WeChatTemplateService weChatTemplateService;


    /**
     * 发送模板信息
     * @param sendTemplate
     * @return
     */
    @PostMapping(value = "/sendTemplate",name = "发送模板信息")
    public ResponseData sendTemplate(@RequestBody SendTemplate sendTemplate){

        boolean success = weChatTemplateService.sendTemplateMessage(sendTemplate);

        if (success){
            return ResponseDataUtil.response(GlobalEnum.success);
        }

        return ResponseDataUtil.response(GlobalEnum.operate_fail);
    }

}
