package com.hly.service;

import com.hly.dao.ITypesDao;
import com.hly.model.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 商品类型业务层
 * @create: 2020-09-22 09:01
 **/
@Service
public class TypesService {
    @Autowired
    private ITypesDao dao;

    /**
     * 查询所有商品类型
     * @return
     */
    public List<Types> selectAll(){
        return dao.selectAll();
    }

    /**
     * 根据ID查询一个商品类型
     * @param id
     * @return
     */
    public Types get(Integer id){
        return dao.findById(id);
    }
}
