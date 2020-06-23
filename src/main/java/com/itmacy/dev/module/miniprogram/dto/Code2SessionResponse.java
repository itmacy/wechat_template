package com.itmacy.dev.module.miniprogram.dto;

import lombok.Data;

/**
 * code2Session
 * Created by itmacy on 2019/7/30.
 */
@Data
public class Code2SessionResponse {


    /**
     * openid	string	用户唯一标识
     session_key	string	会话密钥
     unionid	string	用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     errcode	number	错误码
     errmsg	string	错误信息
     */

    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
