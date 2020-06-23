package com.itmacy.dev.module.officialaccount.controller;

import com.alibaba.fastjson.JSONObject;
import com.itmacy.dev.module.officialaccount.AccessTokenThread;
import com.itmacy.dev.module.officialaccount.model.WeChatMenu;
import com.itmacy.dev.module.officialaccount.service.WeChatMenuService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * 对订阅号的菜单的操作
 *
 * 说明：如果微信公众号设置为开发者模式时，提供对公众号菜单的操作
 *
 */
@RestController
@RequestMapping("/wetChatMenu")
public class WeChatMenuController extends BaseController {

    @Resource
    private WeChatMenuService weChatMenuService;
    @Resource
    private AccessTokenThread accessTokenThread;

    /**
     * 查询全部菜单
     * @return
     */
    @GetMapping(value = "/getMenu",name = "查询菜单")
    public String getMenu() {

        String at = accessTokenThread.getAccessToken();

        JSONObject jsonObject =null;

        if (!StringUtils.isEmpty(at)) {

            logger.info("token为"+at);

            // 调用接口查询菜单
            jsonObject = weChatMenuService.getMenu(at);
            // 判断菜单创建结果
            System.out.println(String.valueOf(jsonObject));
            return String.valueOf(jsonObject);
        }

        return "无数据";
    }

    /**
     * 创建菜单
     * @return
     */
    @PostMapping(value = "/createDefaultMenu",name = "创建默认菜单")
    public int createDefaultMenu() {

        String at = accessTokenThread.getAccessToken();

        int result=0;
        if (!StringUtils.isEmpty(at)) {

            // 调用接口创建菜单
//            result = weChatMenuService.createMenu(weChatMenuService.getFirstMenu(),at);

            //调用接口创建菜单
            result = weChatMenuService.createMenu(at);

            // 判断菜单创建结果
            if (0 == result) {
            	logger.info("菜单创建成功！");
            } else {
            	logger.info("菜单创建失败，错误码：" + result);
            }
        }
        return result ;
    }

    /**
     * 创建菜单
     * @return
     */
    @PostMapping(value = "/createMenu",name = "创建菜单")
    public int createMenu(@RequestBody WeChatMenu weChatMenu) {

        String at = accessTokenThread.getAccessToken();

        int result=0;
        if (!StringUtils.isEmpty(at)) {

            //调用接口创建菜单
            result = weChatMenuService.createMenu(weChatMenu,at);

            // 判断菜单创建结果
            if (0 == result) {
            	logger.info("菜单创建成功！");
            } else {
            	logger.info("菜单创建失败，错误码：" + result);
            }
        }
        return result ;
    }

    /**
     * 删除菜单
     * @return
     */
    @PostMapping(value = "/deleteMenu",name = "删除菜单")
    public int deleteMenu() {

        String at = accessTokenThread.getAccessToken();

        int result=0;
        if (!StringUtils.isEmpty(at)) {
            // 删除菜单
            result = weChatMenuService.deleteMenu(at);
            // 判断菜单删除结果
            if (0 == result) {
            	logger.info("菜单删除成功！");
            } else {
            	logger.info("菜单删除失败，错误码：" + result);
            }
        }
        return  result;
    }


}
