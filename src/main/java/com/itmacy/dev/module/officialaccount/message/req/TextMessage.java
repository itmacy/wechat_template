package com.itmacy.dev.module.officialaccount.message.req;

import lombok.Data;

/**
 * 文本消息
 *
 */
@Data
public class TextMessage extends BaseMessage {

    // 消息内容
    private String Content;


}
