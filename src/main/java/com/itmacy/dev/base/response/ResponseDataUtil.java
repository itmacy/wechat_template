package com.itmacy.dev.base.response;


/**
 * 响应工具类
 * Created by itmacy on 2017/12/21.
 */
public class ResponseDataUtil {


    /**
     * 通过枚举响应
     * @param GlobalEnum
     * @return
     */
    public static ResponseData response(GlobalEnum GlobalEnum){
        ResponseData responseData = new ResponseData();
        responseData.setCode(GlobalEnum.getCode());
        responseData.setMessage(GlobalEnum.getMessage());
        return responseData;

    }

    /**
     * 通过枚举响应
     * @param GlobalEnum
     * @param data
     * @return
     */
    public static ResponseData response(GlobalEnum GlobalEnum, Object data){
        ResponseData responseData = new ResponseData();
        responseData.setCode(GlobalEnum.getCode());
        responseData.setMessage(GlobalEnum.getMessage());
        responseData.setData(data);

        return responseData;

    }

}
