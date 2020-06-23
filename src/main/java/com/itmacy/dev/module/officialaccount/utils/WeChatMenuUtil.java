package com.itmacy.dev.module.officialaccount.utils;

import com.alibaba.fastjson.JSON;

import com.itmacy.dev.module.officialaccount.model.WeChatMenu;
import com.itmacy.dev.util.JsonUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 微信菜单工具类
 * Created by itmacy on 2019/7/31.
 */
public class WeChatMenuUtil {


    /**
     * 读取微信菜单
     * @param path
     * @return
     */
    public static WeChatMenu getWeChatMenuJsonByPath(String path){

        if (StringUtils.isEmpty(path)){
            return null;
        }


        try {
            ClassPathResource classPathResource = new ClassPathResource(path);

            InputStream inputStream = classPathResource.getInputStream();


            InputStreamReader reader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(reader);


            StringBuilder builder = new StringBuilder();

            String s;
            while ((s = bufferedReader.readLine() )!= null){

                if (s.startsWith("//")){
                    continue;
                }

                if (!StringUtils.isEmpty(s)){
                    s = s.trim();
                }

                builder.append(s);
            }

            inputStream.close();

            String result = builder.toString();


            //把读取的字符串转为json对象
            return JSON.parseObject(result, WeChatMenu.class);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public static void main(String[] args) {


        /**
         * 菜单模板格式如下：
         * {
         "button": [
         {
         "name": "扫码",
         "sub_button": [
         {
         "type": "scancode_waitmsg",
         "name": "扫码带提示",
         "key": "rselfmenu_0_0",
         "sub_button": [ ]
         },
         {
         "type": "scancode_push",
         "name": "扫码推事件",
         "key": "rselfmenu_0_1",
         "sub_button": [ ]
         }
         ]
         },
         {
         "name": "发图",
         "sub_button": [
         {
         "type": "pic_sysphoto",
         "name": "系统拍照发图",
         "key": "rselfmenu_1_0",
         "sub_button": [ ]
         },
         {
         "type": "pic_photo_or_album",
         "name": "拍照或者相册发图",
         "key": "rselfmenu_1_1",
         "sub_button": [ ]
         },
         {
         "type": "pic_weixin",
         "name": "微信相册发图",
         "key": "rselfmenu_1_2",
         "sub_button": [ ]
         }
         ]
         },
         {
         "name": "发送位置",
         "type": "location_select",
         "key": "rselfmenu_2_0"
         },
         {
         "type": "media_id",
         "name": "图片",
         "media_id": "MEDIA_ID1"
         },
         {
         "type": "view_limited",
         "name": "图文消息",
         "media_id": "MEDIA_ID2"
         }
         ]
         }
         */
        WeChatMenu weChatMenu = WeChatMenuUtil.getWeChatMenuJsonByPath("wechat/WeChatMenuTemplate.json");

        System.out.println(JsonUtil.prettyPrintJsonString(weChatMenu));

    }
}
