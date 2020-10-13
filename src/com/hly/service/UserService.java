package com.hly.service;

import com.hly.dao.IUserDao;
import com.hly.model.Users;
import com.hly.util.SafeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 用户业务层
 * @create: 2020-09-16 22:31
 **/
@Service
public class UserService {
    @Autowired
    private IUserDao dao;

    public List<Users> getByUsername(String username){
        Users users = new Users();
        users.setUsername(username);
        return dao.findByTiaoJian(users);
    }

    public boolean add(Users user){
        user.setPassword(SafeUtil.encode(user.getPassword()));
        if(dao.insertUsers(user) == 1){
            System.out.println("user:   "+user.toString());
            return true;
        } else {
            return false;
        }
    }

    public Users logIn(Users users){
        users.setPassword(SafeUtil.encode(users.getPassword()));
        List<Users> usersList = dao.findByTiaoJian(users);
        if(usersList != null && usersList.size() == 1){
            return usersList.get(0);
        }else {
            return null;
        }

    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public boolean addressUpdate(Users users){
        return dao.update(users);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public boolean passWordUpdate(Integer id , String passWord){
        Users users = new Users();
        users.setId(id);
        users.setPassword(passWord);
        return dao.update(users);
    }

    public Users get(Integer id){
        Users users = new Users();
        users.setId(id);
        return dao.findByTiaoJian(users).get(0);
    }


}
