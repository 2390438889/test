package com.lujie.mongo_demo.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author 卢杰
 * 用户信息实体类
 * 时间：2018-8-16
 */
@Document(collection = "student")
@Component
public class User implements Serializable {
    @Id
    private String u_id;    //用户编号
    private String u_name;  //用户名
    private String u_pwd;   //用户密码
    private List<Address> u_addr_list;//用户收货地址
    private Integer u_addr_index;   //最新可用索引

    public User() {
        this.u_addr_index=0;
    }

    public User(String u_name, String u_pwd, List<Address> u_addr_list) {
        this.u_addr_index=0;
        this.u_name = u_name;
        this.u_pwd = u_pwd;
        this.u_addr_list = u_addr_list;
    }

    public User(String u_id, String u_name, String u_pwd, List<Address> u_addr_list) {
        this.u_addr_index=0;
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_pwd = u_pwd;
        this.u_addr_list = u_addr_list;
    }


    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public List<Address> getU_addr_list() {
        return u_addr_list;
    }

    public void setU_addr_list(List<Address> u_addr_list) {
        this.u_addr_list = u_addr_list;
    }

    public Integer getU_addr_index() {
        return u_addr_index++;
    }

    public void setU_addr_index(Integer u_addr_index) {
        this.u_addr_index = u_addr_index;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id='" + u_id + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_pwd='" + u_pwd + '\'' +
                ", u_addr_list=" + u_addr_list +
                '}';
    }
}
