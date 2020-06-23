package com.itmacy.dev.base.exception;

import com.alibaba.fastjson.JSON;
import com.itmacy.dev.base.response.GlobalEnum;
import com.itmacy.dev.base.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

/**
 * 全局统一异常处理
 * 注：token过期则刷新token，如果token校验出错则重新登录
 *
 *
 * Created by itmacy on 2017/12/21.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseData defaultErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e){
        logger.error("{}", e);

        ResponseData r = new ResponseData();
        r.setMessage(e.getMessage());

        if (e instanceof NoHandlerFoundException) {
            r.setCode(404);

        } else if (e instanceof AccessDeniedException) {
            r.setCode(HttpServletResponse.SC_FORBIDDEN);
        } else {

//		    ProcessUtils.startRedisService();
//		    ProcessUtils.startMySQLService();

            r.setMessage(GlobalEnum.request_fail.getMessage());
            r.setCode(GlobalEnum.request_fail.getCode());
            r.setData(e.getMessage());


//            logger.error("e.getMessage():{}",e.getMessage());
//            logger.error("e.getCause():{}",e.getCause());
//            logger.error("e.getLocalizedMessage():{}",e.getLocalizedMessage());
//            logger.error("e.getClass():{}",e.getClass());
//            logger.error("e.getStackTrace():{}", JsonUtil.prettyPrintJsonString(e.getStackTrace()));

            logger.error("errormsg:{}",e.toString());


        }
        return r;
    }

    /**
     * http请求方法不支持
     * @param req
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseData httpRequestMethodNotSupportedHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        logger.error("{}", e);

        ResponseData r = new ResponseData();
        r.setMessage(e.getMessage());
        r.setCode(HttpStatus.FORBIDDEN.value());
        return r;
    }


    /**
     * 自定义返回的信息
     * @param response
     * @param ResponseData
     * @throws IOException
     */
    private  static void response(HttpServletRequest request, HttpServletResponse response, ResponseData ResponseData) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //把map转为json字符串
        String jsonString = JSON.toJSONString(ResponseData);
        PrintWriter writer = response.getWriter();
        writer.write(jsonString);
        writer.close();
    }
}
