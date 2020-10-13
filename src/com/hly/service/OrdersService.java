package com.hly.service;

import com.hly.dao.IOrdersDao;
import com.hly.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc
 * @create: 2020-10-12 11:10
 **/
@Service
public class OrdersService {
    @Autowired
    private IOrdersDao dao;

    public Integer save(Users users){
        return dao.insertOrder();
    }
}
