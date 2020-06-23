package com.itmacy.dev.module.miniprogram.utils;

import com.itmacy.dev.util.JsonUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

/**
 * 解码工具类
 * Created by itmacy on 2019/8/27.
 */
public class DecryptUtil {

    private static boolean hasInited = false;

     private static void init() {
        if (hasInited) {
            return;
        }
        Security.addProvider(new BouncyCastleProvider());
        hasInited = true;
    }


    /**
     * 加密数据算法解密
     * @param session_key
     * @param iv
     * @param encryptData
     * @return
     */
    public static String decrypt(String session_key, String iv, String encryptData) {

        String decryptString = "";
        init();
        byte[] sessionKeyByte = Base64.getDecoder().decode(session_key);
        byte[] ivByte = Base64.getDecoder().decode(iv);
        byte[] encryptDataByte = Base64.getDecoder().decode(encryptData);

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key key = new SecretKeySpec(sessionKeyByte, "AES");
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
            algorithmParameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
            byte[] bytes = cipher.doFinal(encryptDataByte);
            decryptString = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return decryptString;
    }


    public static void main(String[] args) {
        //{"session_key":"kuAlOoHmpoRpSlSHeAWoaA==","openid":"oukgC5ZiWKw9GMU2ixZ2Ja4huWwg","unionid":"oBxilv0AyfFt3GL5B1QCNJjX5lKY"}

        String session_key = "kuAlOoHmpoRpSlSHeAWoaA==";
        String iv = "0ck3PByntjOxjlZyYslwQg==";
        String encryptData = "ADvnpY8rMGQqqv+Nf6QAifW+eRHaY9LfqALPY5Mrx0gLakdOZ0Nym5FksNuVRxnyLGn4IQmcpZGUGnLb4vOLawHca69Ggbz17MRAYl5CsoaIASGTYF83/DKCSH9WUUgScZfR3MN6epmc95ZMbY3gLl239gBfgXQai4ve9GOcRTxAV5E+yigWCw1lM+nJy5CtdhmRxeCQZjovrrVMjIg6YTRvCpcXq4dbyjeeRsaXHTYIarspBt8m4XGbNaDciryBheTbONBqu+ut/Xt4iZZW+UtFGyFNDYmxULvVizK3xWNvXkVcKzOVItQ7LhGtVCNMKDhUsEe5uTk9YYqCNStJ4imaOq9DzYWtWugLC0q4tXYWHrIA/8IB8MHJ+PRxsIJ06+QetM5N3b8kFkK9Pu2XEMBHAXYjFf80upudzo8/JkxhzhelnRqS8ZTMZYNjZeDhEHa4v3KWC4z8QOD4mSK10GTAsOjFza8MioWq4+H/9fRJz0SBPRBgCPQIBugsiyol+qdw/SrleylWnwjAqjeJvoGH6KH11k9kvFRKfVoWh38=";
        String decrypt = decrypt(session_key, iv, encryptData);

        /**
         * {"openId":"oukgC5ZiWKw9GMU2ixZ2Ja4huWwg",
         * "nickName":"Raise me up",
         * "gender":1,
         * "language":"zh_CN",
         * "city":"Guangzhou",
         * "province":"Guangdong",
         * "country":"China",
         * "avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEImvQ4191uHVutibrNgRmylOnMicd6LXPLpNAMegics6Od6Azic7oSnHO7V4gKW6G75mNjzkQHCO1BicLw/132",
         * "unionId":"oBxilv0AyfFt3GL5B1QCNJjX5lKY",
         * "watermark":{"timestamp":1566878556,"appid":"wx81dc3bd894aff7f0"}}
         */
        System.out.println(JsonUtil.prettyPrintJsonString(decrypt));
    }

}
