package com.itmacy.dev.module.officialaccount.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itmacy.dev.module.officialaccount.constant.WeChatConstant;
import com.itmacy.dev.module.officialaccount.model.WeChatMenu;
import com.itmacy.dev.module.officialaccount.service.WeChatMenuService;
import com.itmacy.dev.module.officialaccount.utils.WeChatMenuUtil;
import com.itmacy.dev.module.officialaccount.utils.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 对订阅号的菜单的操作
 */
@Service
public class WeChatMenuServiceImpl implements WeChatMenuService {


//    @Value(value = "${wechatWebUrl}")
//    private   String  url;//跳转url

    private static Logger log = LoggerFactory.getLogger(WeChatMenuServiceImpl.class);


    /**
     * 查询菜单
     *
     * @param accessToken 有效的access_token
     * @return
     */
    public JSONObject getMenu(String accessToken) {

        // 拼装创建菜单的url
        String url = WeChatConstant.GET_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        // 调用接口查询菜单
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);

        return jsonObject;
    }


    /**
     * 创建菜单(替换旧菜单)
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public int createMenu(Map<String, Object> menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = WeChatConstant.CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSON.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
                log.error("****"+jsonMenu+"****");
            }
        }
        return result;
    }


    /**
     * 创建菜单（替换旧菜单）
     * @param accessToken
     * @return
     */
    @Override
    public int createMenu(String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = WeChatConstant.CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);

        //获取菜单
        WeChatMenu menu = WeChatMenuUtil.getWeChatMenuJsonByPath(WeChatConstant.MENU_TEMPLATE_URL);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSON.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
                log.error("****"+jsonMenu+"****");
            }
        }
        return result;
    }


    /**
     * 创建菜单
     * @param weChatMenu
     * @param accessToken
     * @return
     */
    @Override
    public int createMenu(WeChatMenu weChatMenu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = WeChatConstant.CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);

        // 将菜单对象转换成json字符串
        String jsonMenu = JSON.toJSONString(weChatMenu);
        // 调用接口创建菜单
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
                log.error("****"+jsonMenu+"****");
            }
        }
        return result;
    }


    /**
     * 删除菜单
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public int deleteMenu(String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = WeChatConstant.DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken);

        // 调用接口创建菜单
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", null);

        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

}
