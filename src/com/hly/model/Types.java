package com.hly.model;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 商品列表实体类
 * @create: 2020-09-22 08:58
 **/
public class Types {
    private Integer id;
    private String name;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
