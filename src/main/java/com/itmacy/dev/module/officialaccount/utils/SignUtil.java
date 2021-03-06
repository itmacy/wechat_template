package com.itmacy.dev.module.officialaccount.utils;

import com.itmacy.dev.module.officialaccount.constant.WeChatConstant;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信请求校验工具类
 */
@Slf4j
public class SignUtil {


	/**
	 * 用    于：	后端
	 * 方 法 名：	checkSignature
	 * 作    用：    校验签名
	 * 创 建 人：	寻欢
	 * 创建时间：	2016/9/19 15:01
	 * 修 改 人：
	 * 修改日期：
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		String[] arr = new String[] {WeChatConstant.TOKEN, timestamp, nonce };
		// 将 token、timestamp、nonce 三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行 sha1 加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			log.error("SHA-1加密失败：{}",e.getMessage());

		}
		log.info("加密后的结果：{}",timestamp);
		log.info("微信返回的结果：{}",signature.toUpperCase());

		content = null;
		// 将 sha1 加密后的字符串可与 signature 对比，标识该请求来源于微信
		return tmpStr != null && tmpStr.equals(signature.toUpperCase());
	}




	/**
	 * 用    于：	后端
	 * 方 法 名：	byteToStr
	 * 作    用：    将字节数组转换成十六进制字符串
	 * 创 建 人：	寻欢
	 * 创建时间：	2016/9/19 15:00
	 * 修 改 人：
	 * 修改日期：
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}



	/**
	 * 用    于：	后端
	 * 方 法 名：	byteToHexStr
	 * 作    用：    将字节转换为十六进制字符串
	 * 创 建 人：	寻欢
	 * 创建时间：	2016/9/19 15:01
	 * 修 改 人：
	 * 修改日期：
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
