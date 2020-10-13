package com.hly.dao;



import com.hly.model.Users;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<Users> findAll();

    int insertUsers(Users users);

    List<Users> findByTiaoJian(Users users);

    boolean update(Users users);

}
