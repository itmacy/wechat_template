package com.itmacy.dev.module.officialaccount.model;

import lombok.Data;

import java.util.List;

/**
 * 微信用户基本信息
 * Created by itmacy on 2019/8/2.
 */
@Data
public class WeChatUserInfo {

    /**
     * {
     "subscribe": 1,
     "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",
     "nickname": "Band",
     "sex": 1,
     "language": "zh_CN",
     "city": "广州",
     "province": "广东",
     "country": "中国",
     "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
     "subscribe_time": 1382694957,
     "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
     "remark": "",
     "groupid": 0,
     "tagid_list":[128,2],
     "subscribe_scene": "ADD_SCENE_QR_CODE",
     "qr_scene": 98765,
     "qr_scene_str": ""
     }
     */

    private Integer subscribe;//1为已关注该公众号，0为未关注
    private String openId;//openId
    private String nickname;//昵称
    private Integer sex;//性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private Integer subscribe_time;//订阅时间
    private String unionid;//unionId
    private String remark;//对订阅者的备注
    private Integer groupid;
    private List<Integer> tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;
}
