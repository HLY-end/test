package com.hly.dao;

import com.hly.model.Tops;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ITopsDao {

    @Select("select * from tops where good_id = #{goodId} and type = #{type}")
    public Tops selectByGOOdIdAndType(@Param("goodId") Integer goodId , @Param("type") byte type);
}
