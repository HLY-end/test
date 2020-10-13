package com.hly.service;

import com.hly.dao.ITopsDao;
import com.hly.dao.ITypesDao;
import com.hly.model.Tops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 今日推荐业务层
 * @create: 2020-09-24 10:15
 **/
@Service
public class TopsService {
    @Autowired
    private ITopsDao dao;

    /**
     * 通过商品ID和类型获取
     * @param goodId
     * @param type
     * @return
     */
    public Tops getByGoodIdAndType(Integer goodId , byte type){
        return dao.selectByGOOdIdAndType(goodId,type);
    }
}
