package com.itmacy.dev.module.officialaccount.message.resp;

import lombok.Data;

/**
 * 音乐消息
 */
@Data
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

}
