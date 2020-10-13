package com.hly.service;

import com.hly.dao.IGoodsDao;
import com.hly.model.Goods;
import com.hly.model.Tops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 商品业务层
 * @create: 2020-09-20 22:26
 **/
@Service
public class GoodsService {
    @Autowired
    private IGoodsDao dao;
    @Autowired
    private TypesService typesService;
    @Autowired
    private TopsService topsService;

    public List<Goods> findToDay(){
        return dao.findToDay();
    }

    public List<Goods> findHot(){
        return dao.findHot();
    }

    /**
     * 商品列表查询
     * @param typeId
     * @param page
     * @param size
     * @return
     */
    public List<Goods> getListByType(Integer typeId , Integer page , Integer size){
        return typeId > 0 ? packGood(dao.findType(typeId,size*(page-1),size)):this.getList(page,size);
    }

    /**
     * 通过推荐搜索
     * @param type
     * @param page
     * @param size
     * @return
     */
    public List<Goods> getListByType(byte type , int page , int size){
        return packGood(dao.selectListByTopType(type,size * (page-1) , size));
    }

    /**
     * 今日推荐计数
     * @param type
     * @return
     */
    public long getCountByTopType(byte type){
        return dao.selectCountByTopType(type);
    }

    /**
     * 热销排行
     * @param page
     * @param size
     * @return
     */
    public List<Goods> getListByHot(int page , int size){
        return dao.selectListByHot((page-1)*size , size);
    }

    public List<Goods> packGood(List<Goods> list){
        for (Goods goods:list
             ) {
            goods = packGood(goods);
        }
        return list;
    }

    public Goods packGood(Goods goods){
        if(goods != null){
            goods.setType(typesService.get(goods.getTypeId()));
            goods.setTop(Objects.nonNull(topsService.getByGoodIdAndType(goods.getId(), Tops.TYPE_TODAY )));
        }
        return goods;
    }

    /**
     * 没有type_id,就根据热销排行
     * @param page
     * @param size
     * @return
     */
    public List<Goods> getList(Integer page,Integer size){
        return packGood(dao.selectListByNew((page - 1)*size,size));
    }

    public Goods getById(Integer id){
        return packGood(dao.selectById(id));
    }

    /**
     * 根据ID计算总条数
     * @param typeId
     * @return
     */
    public long getCountByType(int typeId){
        return typeId > 0 ? dao.selectCountByTypeId(typeId) : this.getCount();
    }

    /**
     * 计算全部商品的总条数
     * @return
     */
    public long getCount(){
        return dao.selectCount();
    }
}
