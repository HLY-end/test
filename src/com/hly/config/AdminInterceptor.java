package com.hly.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 后台拦截器
 * @create: 2020-09-07 16:03
 **/
public class AdminInterceptor extends HandlerInterceptorAdapter {

    /**
     * 检测登录状态
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(uri.contains("img") || uri.contains("js") || uri.contains("css")|| uri.contains("login")|| uri.contains("logout")){
            return true;//不拦截路径
        }

        Object admin = request.getSession().getAttribute("admin");
        if(Objects.nonNull(admin) && !admin.toString().trim().isEmpty()){
            return true;//登录验证通过
        }
        response.sendRedirect("login.jsp");
        return false; //其它情况一侓拦截
    }
}
