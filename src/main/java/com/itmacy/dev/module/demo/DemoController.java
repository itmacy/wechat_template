package com.itmacy.dev.module.demo;

import com.itmacy.dev.base.response.GlobalEnum;
import com.itmacy.dev.base.response.ResponseData;
import com.itmacy.dev.base.response.ResponseDataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * demo控制器
 * @Author:itmacy
 * @Date:2020/6/22
 */
@RestController
@RequestMapping("/demo")
public class DemoController {


    @GetMapping(value = "/test",name = "测试")
    public ResponseData test(){
        return ResponseDataUtil.response(GlobalEnum.success,"hello");
    }


}
