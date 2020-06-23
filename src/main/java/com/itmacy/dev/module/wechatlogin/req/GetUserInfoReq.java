package com.itmacy.dev.module.wechatlogin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取用户信息
 * @Author:itmacy
 * @Date:2020/6/22
 */
@Data
public class GetUserInfoReq {

    @ApiModelProperty(value = "accessToken")
    private String accessToken;

    @ApiModelProperty(value = "openId")
    private String openId;
}
