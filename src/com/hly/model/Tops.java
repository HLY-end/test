package com.hly.model;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 今日推荐实体类
 * @create: 2020-09-20 22:08
 **/
public class Tops {
    public static final byte TYPE_TODAY = 1;

    private Integer id;
    private Integer type;
    private Integer goodId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toString() {
        return "Tops{" +
                "id=" + id +
                ", type=" + type +
                ", goodId=" + goodId +
                '}';
    }
}
