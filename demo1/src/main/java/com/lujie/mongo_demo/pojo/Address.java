package com.lujie.mongo_demo.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class Address implements Serializable {
    private String add_id;  //地址编号
    private String add_name;//收货人姓名
    private Integer add_status;//地址状态   0为无效地址，1为有效地址，2为默认地址
    private String add_phone;//联系电话
    private Date add_date;//地址创建时间
    private String add_info;    //地址信息

    public Address() {
    }

    public Address(String add_id, String add_info) {
        this.add_id = add_id;
        this.add_info = add_info;
    }

    public Address(String add_name, Integer add_status, String add_phone, Date add_date, String add_info) {
        this.add_name = add_name;
        this.add_status = add_status;
        this.add_phone = add_phone;
        this.add_date = add_date;
        this.add_info = add_info;
    }

    public Address(String add_id, String add_name, Integer add_status, String add_phone, Date add_date, String add_info) {
        this.add_id = add_id;
        this.add_name = add_name;
        this.add_status = add_status;
        this.add_phone = add_phone;
        this.add_date = add_date;
        this.add_info = add_info;
    }

    public String getAdd_id() {
        return add_id;
    }

    public void setAdd_id(String add_id) {
        this.add_id = add_id;
    }

    public String getAdd_info() {
        return add_info;
    }

    public void setAdd_info(String add_info) {
        this.add_info = add_info;
    }

    public String getAdd_name() {
        return add_name;
    }

    public void setAdd_name(String add_name) {
        this.add_name = add_name;
    }

    public Integer getAdd_status() {
        return add_status;
    }

    public void setAdd_status(Integer add_status) {
        this.add_status = add_status;
    }

    public String getAdd_phone() {
        return add_phone;
    }

    public void setAdd_phone(String add_phone) {
        this.add_phone = add_phone;
    }

    public Date getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }

    @Override
    public String toString() {
        return "Address{" +
                "add_id='" + add_id + '\'' +
                ", add_name='" + add_name + '\'' +
                ", add_status=" + add_status +
                ", add_phone='" + add_phone + '\'' +
                ", add_date=" + add_date +
                ", add_info='" + add_info + '\'' +
                '}';
    }
}
