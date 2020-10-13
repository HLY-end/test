package com.hly.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 统一处理业务异常
 * @create: 2020-09-16 11:05
 **/
@ControllerAdvice
public class ExceptionConfig {

    /**
     * 业务异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(MyException.class)
    public String exception(MyException exception , HttpServletRequest request){
        request.setAttribute("msg",exception);
        return "/index/error.jsp";
    }

    /**
     * 默认异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String exception(Exception exception , HttpServletRequest request){
        request.setAttribute("msg","系统错误！");
        return "/index/error.jsp";
    }

    /**
     * 自定义异常
     */
    public static class MyException extends Exception{

        public MyException(String msg){
            super(msg);
        }
    }
}
