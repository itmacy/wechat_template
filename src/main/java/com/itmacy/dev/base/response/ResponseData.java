package com.itmacy.dev.base.response;


import io.swagger.annotations.ApiModelProperty;

/**
 * 响应对象
 * Created by itmacy on 2017/12/15.
 */
public class ResponseData<T> {

    @ApiModelProperty(value = "响应码",notes = "当code为0时，表示成功")
    private Integer code;
    @ApiModelProperty(value = "响应内容")
    private T data;
    @ApiModelProperty(value = "响应信息")
    private String message;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
