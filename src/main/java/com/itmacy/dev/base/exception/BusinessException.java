package com.itmacy.dev.base.exception;

/**
 * 业务异常
 * @Author:itmacy
 * @Date:2019/11/18
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }


    public BusinessException(String message) {
        super(message);
    }


    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
