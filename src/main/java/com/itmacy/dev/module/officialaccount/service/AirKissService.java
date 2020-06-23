package com.itmacy.dev.module.officialaccount.service;


import com.itmacy.dev.module.officialaccount.model.AirKissConfig;

/**
 * 获取airKiss服务接口
 * Created by itmacy on 2019/9/25.
 */
public interface AirKissService {

    /**
     * 获取airKiss配置
     * @return
     */
    AirKissConfig getAirKissConfig(String htmlUrl);

}
