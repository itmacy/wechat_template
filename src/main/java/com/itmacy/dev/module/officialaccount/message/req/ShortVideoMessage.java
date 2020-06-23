package com.itmacy.dev.module.officialaccount.message.req;

import lombok.Data;

/**
 * 小视频消息
 * Created by itmacy on 2019/7/31.
 */
@Data
public class ShortVideoMessage {


    private String MediaId;//视频消息媒体id
    private String ThumbMediaId;//视频消息缩略图的媒体id
}
