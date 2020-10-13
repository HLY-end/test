package com.hly.controller;

import com.hly.model.*;
import com.hly.service.CartsService;
import com.hly.service.GoodsService;
import com.hly.service.OrdersService;
import com.hly.service.TypesService;
import com.hly.util.PageUtil;
import org.junit.runners.Parameterized;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc
 * @create: 2020-09-21 21:27
 **/
@Controller
@RequestMapping("/index")
public class IndexController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private TypesService typesService;
    @Resource
    private CartsService cartsService;
    @Resource
    private OrdersService ordersService;

    /**
     * 首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request , HttpServletResponse response){
        request.setAttribute("flag",1);
        request.setAttribute("todayList",goodsService.getListByType(Tops.TYPE_TODAY,1,6));
        request.setAttribute("hotList",goodsService.getListByHot(1,8));
        List<Types> typesList = typesService.selectAll();
        List<Map<String , Object>> dataList = new ArrayList<>();
        for (Types t:typesList
             ) {
            Map<String , Object> map = new HashMap<>();
            map.put("type",t);
            map.put("goodList",goodsService.getListByType(t.getId(),1,15));
            dataList.add(map);
        }
        request.setAttribute("dataList",dataList);
        Users users = (Users) request.getSession().getAttribute("user");
        request.setAttribute("cartCount",cartsService.getCount(users.getId()));
        return "index.jsp";
    }

    /**
     * 商品列表
     * @param request
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/type")
    public String type(HttpServletRequest request,
                       @RequestParam(required = false,defaultValue = "0")int id ,
                       @RequestParam(required = false,defaultValue = "1")Integer page ,
                       @RequestParam(required = false,defaultValue = "4")int size) {
        request.setAttribute("type" , typesService.get(id));
        request.setAttribute("goodList",goodsService.getListByType(id , page , size));
        request.setAttribute("pageHtml", PageUtil.getPageHtml(request,goodsService.getCountByType(id),page,size));
        return "/index/goods.jsp";
    }

    /**
     * 今日推荐
     * @param request
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/today")
    public String today(HttpServletRequest request,
                        @RequestParam(required = false , defaultValue = "1")int page,
                        @RequestParam(required = false , defaultValue = "4")int size){
        request.setAttribute("flag",2);
        request.setAttribute("goodList",goodsService.getListByType(Tops.TYPE_TODAY,page,size));
        request.setAttribute("pageHtml",PageUtil.getPageHtml(request,goodsService.getCountByTopType(Tops.TYPE_TODAY),page,size));
        return "/index/goods.jsp";
    }

    /**
     * 热销排行
     * @param request
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/hot")
    public String hot(HttpServletRequest request,
                      @RequestParam(required = false , defaultValue = "1")int page,
                      @RequestParam(required = false , defaultValue = "4")int size){
        request.setAttribute("flag",3);
        request.setAttribute("goodList",goodsService.getListByHot(page,size));
        request.setAttribute("pageHtml",PageUtil.getPageHtml(request,goodsService.getCount(),page,size));
        return "/index/goods.jsp";
    }

    /**
     * 新品上市
     * @param request
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/new")
    public String news(HttpServletRequest request,
                      @RequestParam(required = false , defaultValue = "1")int page,
                      @RequestParam(required = false , defaultValue = "4")int size){
        request.setAttribute("flag",4);
        request.setAttribute("goodList",goodsService.getList(page,size));
        request.setAttribute("pageHtml",PageUtil.getPageHtml(request,goodsService.getCount(),page,size));
        return "/index/goods.jsp";
    }

    /**
     * 商品详情
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public String detail(HttpServletRequest request,
                         @RequestParam(required = false , defaultValue = "1")int id){
        request.setAttribute("good",goodsService.getById(id));
        request.setAttribute("todayList",goodsService.getListByType(Tops.TYPE_TODAY,1,2));
        return "/index/detail.jsp";
    }

    /**
     * 添加购物车
     * @param request
     * @param goodId
     * @return
     */
    @PostMapping("/cartBuy")
    public @ResponseBody boolean cartBuy(HttpServletRequest request , Integer goodId){
        Users users = (Users) request.getSession().getAttribute("user");
        return cartsService.save(goodId,users.getId());
    }

    /**
     * 显示购物车
     * @return
     */
    @GetMapping("/cart")
    public String cart(HttpServletRequest request , HttpSession session){
        Users users = (Users) session.getAttribute("user");
        List<Carts> cartsList = cartsService.getList(users.getId());
        request.setAttribute("cartList",cartsList);
        request.setAttribute("cartCount",cartsService.getCount(users.getId()));
        request.setAttribute("cartTotal",cartsService.getCartTotal(cartsList));
        return "/index/cart.jsp";
    }

    /**
     * 增加购物车商品的数量
     * @param cartId
     * @return
     */
    @PostMapping("/cartAdd")
    public @ResponseBody boolean cartAdd(@RequestParam("id")Integer cartId){
        return cartsService.add(cartId);
    }

    /**
     * 减少购物车商品的数量
     * @param carId
     * @return
     */
    @PostMapping("/cartLess")
    public @ResponseBody boolean cartLess(@RequestParam("id")Integer carId){
        return cartsService.less(carId);
    }

    /**
     * 删除购物车的商品
     * @param carId
     * @return
     */
    @PostMapping("/cartDelete")
    public @ResponseBody boolean cartDelete(@RequestParam("id")Integer carId){
        return cartsService.delete(carId);
    }

    /**
     * 刷新购物车总金额
     * @param session
     * @return
     */
    @GetMapping("/cartTotal")
    public @ResponseBody Float cartTotal(HttpSession session){
        Users users = (Users) session.getAttribute("user");
        return cartsService.getCartTotal(cartsService.getList(users.getId()));
    }

    @GetMapping("/cartCount")
    public @ResponseBody Long cartCount(HttpSession session){
        Users users = (Users) session.getAttribute("user");
        return cartsService.getCount(users.getId());
    }

    /**
     * 生成订单，跳转到结算页面
     * @return
     */
    @GetMapping("/orderSave")
    public String orderSave(HttpSession session){
        Users users = (Users) session.getAttribute("user");
        int orderId = ordersService.save(users);
        session.removeAttribute("cartCount");
        return "redirect:orderPay?id="+orderId;
    }

    @GetMapping("/orderPay")
    public String orderPay(@RequestParam("id")Integer orderId){
        return "/index/index.jsp";
    }

}
