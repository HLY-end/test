package com.hly.dao;

import com.hly.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodsDao {

    public List<Goods> findToDay();

    public List<Goods> findHot();

    public List<Goods> findType(@Param("typeId")Integer typeId,@Param("begin")Integer begin,@Param("size")Integer size);

    public int selectCountByTypeId(int typeId);

    public int selectCount();

    public List<Goods> selectListByTopType(@Param("type")byte type , @Param("begin")int begin , @Param("size")int size );

    public int selectCountByTopType(@Param("type") byte type);

    public List<Goods> selectListByHot( @Param("begin")int begin , @Param("size")int size );

    public List<Goods> selectListByNew( @Param("begin")int begin , @Param("size")int size );

    public Goods selectById(Integer id);
}
