package com.itmacy.dev.module.miniprogram.dto.req;

import lombok.Data;

/**
 * 获取解密数据请求
 * Created by itmacy on 2019/8/27.
 */
@Data
public class GetDecryptDataReq {

    private String session_key;

    private String iv;

    private String encryptData;
}
