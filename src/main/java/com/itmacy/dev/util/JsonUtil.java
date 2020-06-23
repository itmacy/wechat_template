package com.itmacy.dev.util;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * json工具类
 * Created by itmacy on 2018/1/20.
 */
public class JsonUtil {

    /**
     * 对象转json
     * @param obj
     * @return
     * @throws Exception
     */
    public static String obj2json(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json转对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T json2obj(String jsonStr, Class<T> clazz)  {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * map转json
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T map2obj(Map<?, ?> map, Class<T> clazz)  {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    /**
     * json转map
     * @param jsonStr
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Map<String, Object> json2map(String jsonStr)  {
        return JSON.parseObject(jsonStr, Map.class);
    }


    /**
     * 格式化输出json字符串
     * @param jsonString
     * @return
     */
    public static String prettyPrintJsonString(String jsonString){

        StringBuilder prettyJsonString = new StringBuilder();

        for(int i = 0, j = 0, k = 0, ii; i < jsonString.length(); i ++) {//k:缩进，j:""个数

            String charString = jsonString.charAt(i) + "";

            if (j % 2 == 0 && charString.equals("}")) {
                k--;
                for (ii = 0; ii < k; ii++){
                    charString = "    " + charString;
                }

                charString = "\n" + charString;
            } else if (j % 2 == 0 && charString.equals("{")) {
                charString += "\n";
                k++;

                for (ii = 0; ii < k; ii++) {
                    charString += "    ";
                }

            } else if (j % 2 == 0 && charString.equals(",")) {
                charString += "\n";

                for (ii = 0; ii < k; ii++) {
                    charString += "    ";
                }

            } else if (charString.equals("\"")){

                j++;
            }

            prettyJsonString.append(charString);
        }


        return prettyJsonString.toString();


    }


    /**
     * 格式化输出json字符串
     * @param object
     * @return
     */
    public static String prettyPrintJsonString(Object object){

        //把对象转为json格式字符串
        String jsonString = JSON.toJSONString(object);

        StringBuilder prettyJsonString = new StringBuilder();

        for(int i = 0, j = 0, k = 0, ii; i < jsonString.length(); i ++) {//k:缩进，j:""个数

            String charString = jsonString.charAt(i) + "";

            if (j % 2 == 0 && charString.equals("}")) {
                k--;
                for (ii = 0; ii < k; ii++){
                    charString = "    " + charString;
                }

                charString = "\n" + charString;
            } else if (j % 2 == 0 && charString.equals("{")) {
                charString += "\n";
                k++;

                for (ii = 0; ii < k; ii++) {
                    charString += "    ";
                }

            } else if (j % 2 == 0 && charString.equals(",")) {
                charString += "\n";

                for (ii = 0; ii < k; ii++) {
                    charString += "    ";
                }

            } else if (charString.equals("\"")){

                j++;
            }

            prettyJsonString.append(charString);
        }


        return prettyJsonString.toString();


    }


//    public static void main(String[] args) {
//
//        String jsonString = "{\"address\":\"gz\",\"birth\":\"2018-08-30\",\"email\":\"2782889552@qq.com\"}";
//
//        System.out.println("输出漂亮格式前：");
//        System.out.println(jsonString);
//        System.out.println("输出漂亮格式后：");
//        System.out.println(prettyPrintJsonString(jsonString));
//    }


}
