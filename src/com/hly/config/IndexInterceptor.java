package com.hly.config;

import com.hly.model.Types;
import com.hly.service.TypesService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 前台拦截器
 * @create: 2020-09-07 16:37
 **/
public class IndexInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private TypesService typesService;

//    @Resource
//    private CartService cartService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle方法执行了！");
        request.setAttribute("typeList",typesService.selectAll());
        String uri = request.getRequestURI();
        if(uri.contains("index/cart") || uri.contains("index/order") || uri.contains("index/my")){
            Object object = request.getSession().getAttribute("user");
            if(Objects.isNull(object)){
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }
}
