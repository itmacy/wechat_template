package com.itmacy.dev.module.officialaccount.message.req;

import lombok.Data;

/**
 * 视频消息
 * Created by itmacy on 2019/7/31.
 */
@Data
public class VideoMessage extends BaseMessage{

    private String MediaId;//视频消息媒体id，可以调用获取临时素材接口拉取数据。
    private String ThumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
}
