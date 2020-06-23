package com.itmacy.dev.module.officialaccount.model;

import lombok.Data;

/**
 * airKiss配置
 * Created by itmacy on 2019/9/25.
 */
@Data
public class AirKissConfig {
    private boolean debug;
    private String appId;
    private String timestamp;
    private String nonce;
    private String signature;
    private String title;
    private String link;
    private String signType;
    private String packageName;
}
