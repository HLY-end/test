package com.hly.controller;

import com.hly.config.ExceptionConfig;
import com.hly.model.Users;
import com.hly.service.CartsService;
import com.hly.service.GoodsService;
import com.hly.service.UserService;
import com.hly.util.SafeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 用户登录控制器
 * @create: 2020-09-09 10:00
 **/
@Controller
@RequestMapping("/index")
public class UserController {
    @Resource()
    private UserService userService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private CartsService cartsService;

    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return "/index/register.jsp";
    }

    @PostMapping("/register")
    public String register(Users user, HttpServletRequest request) throws ExceptionConfig.MyException {
        if (user.getUsername().isEmpty()) {
            request.setAttribute("msg", "用户名不能为空！");
        } else if (Objects.nonNull(userService.getByUsername(user.getUsername()))) {  //Objects.nonNull()判断一个对象是否为空
            request.setAttribute("msg", "用户名已经存在！");
        } else {
            if(userService.add(user)){
                request.setAttribute("msg", "注册成功 可以去登录了！");
                return "/index/login.jsp";
            }else {
                throw new ExceptionConfig.MyException("系统出错！");
            }
        }
        return "/index/register.jsp";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "/index/login.jsp";
    }

    @PostMapping("/login")
    public String login(Users users , HttpServletRequest request){
        Users users1 = userService.logIn(users);
//        System.out.println("users1.toString():    "+users1.toString());
        if(!Objects.isNull(users1)){
            request.getSession().setAttribute("user",users1);
            request.getSession().setAttribute("cartCount",cartsService.cartCount(users1.getId()));
            return "redirect:index";
        }else {
            request.setAttribute("msg","账号或密码错误！");
            return "/index/login.jsp";
        }
    }

    @GetMapping("/address")
    public String address(){
        return "/index/address.jsp";
    }

    @PostMapping("/addressUpdate")
    public String addressUpdate(Users users , HttpServletRequest request , HttpSession session) throws ExceptionConfig.MyException {
        Users users1 = (Users)session.getAttribute("user");
        users.setId(users1.getId());
//        System.out.println("user:  "+users);
//        System.out.println("user1:  "+users1);
        if(userService.addressUpdate(users)){
            request.setAttribute("msg","修改成功！");
            Users users2 = userService.get(users1.getId());
            session.setAttribute("user", users2);
            return "address.jsp";
        } else {
            throw new ExceptionConfig.MyException("系统出错！");
        }
    }

    @GetMapping("/password")
    public String password(){
        return "password.jsp";
    }

    @PostMapping("/passwordUpdate")
    public String passwordUpdate(@RequestParam("password") String passWord , @RequestParam("passwordNew") String passWordNew , HttpSession session ,HttpServletRequest request) throws ExceptionConfig.MyException {
        Users users = (Users) session.getAttribute("user");
        Users users1 = userService.get(users.getId());
        if(!users1.getPassword().equals(SafeUtil.encode(passWord))){
            request.setAttribute("msg","原密码错误！");
        }else {
            if(userService.passWordUpdate(users1.getId(),SafeUtil.encode(passWordNew))){
                request.setAttribute("msg","修改密码成功！");
            }else {
                throw new ExceptionConfig.MyException("系统出错！");
            }
        }
         return "password.jsp";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("cartCount");
        return "redirect:index";
    }

}
