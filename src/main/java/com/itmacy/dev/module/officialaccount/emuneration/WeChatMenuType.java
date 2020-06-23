package com.itmacy.dev.module.officialaccount.emuneration;

/**
 * 微信菜单类型
 * Created by itmacy on 2019/7/31.
 */
public enum  WeChatMenuType {


    click("click"),//点击事件
    view("view"),//跳转url
    scancode_push("scancode_push"),//调起扫一扫工具
    scancode_waitmsg("scancode_waitmsg"),//提示调起扫一扫工具
    pic_sysphoto("pic_sysphoto"),//调起系统相机
    pic_photo_or_album("pic_photo_or_album"),//调起“拍照”或者“从手机相册选择”
    pic_weixin("pic_weixin"),//调起微信相册
    location_select("location_select"),//弹出地理位置选择器
    media_id("media_id"),//下发消息，永久素材类型可以是图片、音频、视频、图文消息
    view_limited("view_limited"),//跳转图文消息URL，永久素材类型只支持图文消息
    miniprogram("miniprogram"),//微信小程序


    ;

    private String type;
    WeChatMenuType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
