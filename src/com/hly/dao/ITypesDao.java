package com.hly.dao;

import com.hly.model.Types;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITypesDao {

    @Select("select * from types order by num")
    List<Types> selectAll();

    @Select("select * from types where id = #{id} ")
    public Types findById(Integer id);
}
