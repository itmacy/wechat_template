package com.itmacy.dev.module.officialaccount.message.resp;


import lombok.Data;

/**
 * 微信设备硬件
 */
@Data
public class WxDeviceResponseEvent extends BaseMessage{

	private String DeviceType; //设备类型，目前为“公众账号原始ID”
	private String DeviceID;  //设备ID，第三方提供
	private String Content; //消息内容，BASE64编码
	private String SessionID; //微信客户端生成的session id，用于request和response对应，因此响应中该字段第三方需要原封不变的带回
	private String MsgId; //消息id，微信后台生成
	private String OpenID; //微信用户账号的OpenID

}
