package com.itmacy.dev.module.officialaccount.template.resp;

import lombok.Data;

/**
 * 响应bean
 * Created by itmacy on 2019/7/31.
 */
@Data
public class ResponseBean {

    private Integer errcode;
    private String errmsg;
    private String msgid;
}
