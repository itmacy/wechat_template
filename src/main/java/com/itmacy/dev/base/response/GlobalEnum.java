package com.itmacy.dev.base.response;

/**
 * 响应码枚举
 * Created by itmacy on 2017/12/21.
 */
public enum GlobalEnum {

    //0-100全局
    success(0,"success"),//成功
    system_exception(1,"system exception"),//系统异常,后台出错
    request_fail(2,"request fail"),//请求失败，如请求方式，请求体或json解析异常或后台异常
    query_fail(3,"query fail"),//查询失败
    operate_fail(4,"operate fail"),//操作失败
    parameter_error(5,"parameter error"),//参数错误


    ;


    private Integer code;
    private String message;
    GlobalEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
         return code;
    }
    public String getMessage(){
        return message;
    }

}
