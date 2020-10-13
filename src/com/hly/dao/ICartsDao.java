package com.hly.dao;

import com.hly.model.Carts;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ICartsDao {

    @Select("select ifNull(sum(amount),0) from carts c join users u on c.user_id = u.id where c.user_id = #{id}")
    public long cartCount(@Param("id") Integer id);

    @Select("select * from carts where good_id = #{goodId} and user_id = #{userId} limit 1")
    public Carts selectByUserIdAndGoodId(@Param("goodId")Integer goodId , @Param("userId")Integer userId);

    @Insert("insert carts (amount,good_id,user_id) value(#{amount},#{goodId},#{userId})")
    public boolean insertCarts(Carts carts);

    @Select("select * from carts where user_id = #{userId}")
    @Results(id = "carts" , value = {
            @Result(id = true , property = "id" , column = "id"),
            @Result( property = "amount" , column = "amount"),
            @Result( property = "goodId" , column = "good_id"),
            @Result( property = "userId" , column = "user_id"),
            @Result( property = "good" , column = "good_id" ,
                    one = @One(
                            select = "com.hly.dao.IGoodsDao.selectById",fetchType = FetchType.LAZY
                    ))
    })
    public List<Carts> selectCartsByUserId(@Param("userId") Integer userId);

    @Select("select * from carts where id = #{id}")
    public Carts selectCartsById(@Param("id") Integer cartId);

    @Update("update carts set amount = amount+#{amount} where id = #{id}")
    public boolean updateAmount(@Param("id") Integer id , @Param("amount") Integer amount);

    @Delete("delete from carts where id = #{id}")
    public boolean delete(@Param("id") Integer cartId);

}
