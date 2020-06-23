package com.itmacy.dev.module.officialaccount.message.req;

import lombok.Data;

/**
 * 音频消息
 *
 */
@Data
public class VoiceMessage extends BaseMessage {
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;

}
