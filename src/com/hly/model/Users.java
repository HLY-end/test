package com.hly.model;

import com.hly.util.SystemStringUtils;

import java.io.Serializable;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 用户实体类
 * @create: 2020-09-09 10:04
 **/
public class Users implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = SystemStringUtils.strNotNullTrim(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = SystemStringUtils.strNotNullTrim(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = SystemStringUtils.strNotNullTrim(name);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = SystemStringUtils.strNotNullTrim(phone);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = SystemStringUtils.strNotNullTrim(address);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
