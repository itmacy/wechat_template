package com.itmacy.dev.module.officialaccount.message.resp;

import lombok.Data;

/**
 * 文本消息消息体
 */
@Data
public class TextMessage extends BaseMessage {

	// 回复的消息内容
	private String Content;

}
