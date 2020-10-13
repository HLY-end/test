package com.hly.service;

import com.hly.dao.ICartsDao;
import com.hly.model.Carts;
import com.hly.model.Goods;
import com.hly.model.Tops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 购物车业务层
 * @create: 2020-09-29 09:35
 **/
@Service
public class CartsService {
    @Autowired
    ICartsDao dao;

    public long cartCount(Integer id){
        return dao.cartCount(id);
    }

    public boolean save(Integer goodId , Integer userId){
        Carts carts = dao.selectByUserIdAndGoodId(goodId,userId);
        if(Objects.nonNull(carts)){
            return dao.updateAmount(carts.getId(),1);
        }
        carts = new Carts();
        carts.setGoodId(goodId);
        carts.setUserId(userId);
        carts.setAmount(1);//默认数量为1
        return dao.insertCarts(carts);
    }

    public List<Carts> getList(Integer userId){
        return packCarts(dao.selectCartsByUserId(userId));
    }

    public List<Carts> packCarts(List<Carts> list){
        for(int i = 0; i < list.size(); i++){

            list.get(i).setTotal(list.get(i).getGood().getPrice()*list.get(i).getAmount());

        }
        return list;
    }


    public Long getCount(Integer userId){
        return dao.cartCount(userId);
    }

    public Float getCartTotal(List<Carts> cartsList){
        float cartTotal = 0.0f;
        for (Carts cart:cartsList
             ) {
            cartTotal += cart.getTotal();
        }
        return cartTotal;
    }

    public boolean add(Integer cartId){
        return dao.updateAmount(cartId,1);
    }

    public boolean less(Integer carId){
        Carts carts = dao.selectCartsById(carId);
        if(carts.getAmount() <= 1){
            return dao.delete(carId);
        }
        return dao.updateAmount(carId,-1);
    }

    public boolean delete(Integer cartId){
        return dao.delete(cartId);
    }


}
