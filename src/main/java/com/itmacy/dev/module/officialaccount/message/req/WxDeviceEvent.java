package com.itmacy.dev.module.officialaccount.message.req;

import lombok.Data;

/**
 * 微信硬件绑定事件
 * @author Administrator
 *
 */
@Data
public class WxDeviceEvent extends BaseMessage{

	private String Event;
	private String DeviceType;
	private String Content;
	private String DeviceID;
	private String SessionID;
	private String OpenID;


}
