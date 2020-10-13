package com.hly.model;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 购物车实体类
 * @create: 2020-09-28 16:43
 **/
public class Carts {
    private Integer id;
    private Integer amount;
    private Integer goodId;
    private Integer userId;
    private Float total;
    private Goods good;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", amount=" + amount +
                ", goodId=" + goodId +
                ", userId=" + userId +
                ", total=" + total +
                ", good=" + good +
                '}';
    }
}
