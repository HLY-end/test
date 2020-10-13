package com.hly.controller;

import com.hly.service.AdminService;
import com.hly.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc
 * @create: 2020-09-08 14:40
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TypesService typeService;

    /**
     * 管理员登录
     */
    @GetMapping("/login")
    public String log() {
        return "/admin/login.jsp";
    }

    /**
     * 退出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "/admin/login.jsp";
    }

}
