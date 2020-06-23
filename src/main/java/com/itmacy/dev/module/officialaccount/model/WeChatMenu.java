package com.itmacy.dev.module.officialaccount.model;

import com.itmacy.dev.module.officialaccount.emuneration.WeChatMenuType;
import lombok.Data;

import java.util.List;

/**
 * 微信菜单
 * Created by itmacy on 2019/7/31.
 */
@Data
public class WeChatMenu {


    private List<Button> button;


    @Data
    public static class Button{

        private List<Button> sub_button;//二级菜单数组，个数应为1~5个
        private WeChatMenuType type;//菜单的响应动作类型，必须
        private String name;//菜单标题，不超过16个字节，子菜单不超过60个字节,必须
        private String key;//click等点击类型必须
        private String url;//view、miniprogram类型必须
        private String media_id;//media_id类型和view_limited类型必须
        private String appid;//miniprogram类型必须	小程序的appid
        private String pagepath ;//miniprogram类型必须	小程序的页面路径

    }

}
