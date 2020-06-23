package com.itmacy.dev.module.officialaccount.service;

import com.alibaba.fastjson.JSONObject;
import com.itmacy.dev.module.officialaccount.model.WeChatMenu;


/**
 * 微信菜单服务接口
 */
public interface WeChatMenuService {

    /**
     * 查询菜单
     * @param accessToken
     * @return
     */
     JSONObject getMenu(String accessToken);


    /**
     * 创建菜单
     * @param accessToken
     * @return
     */
    int createMenu(String accessToken);


    /**
     * 创建菜单
     * @param weChatMenu
     * @param accessToken
     * @return
     */
    int createMenu(WeChatMenu weChatMenu, String accessToken);

    /**
     * 删除菜单
     * @param accessToken
     * @return
     */
     int deleteMenu(String accessToken);

}
