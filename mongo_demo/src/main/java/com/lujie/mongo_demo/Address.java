package com.lujie.mongo_demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document(collection = "student")
public class Address implements Serializable {
    @Id
    private String add_id;
    private String add_info;
    @PersistenceConstructor
    public Address(String add_id, String add_info) {
        this.add_id = add_id;
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

    public Address(String add_info) {
        this.add_info = add_info;

    }

    @Override
    public String toString() {
        return "Address{" +
                "add_id='" + add_id + '\'' +
                ", add_info='" + add_info + '\'' +
                '}';
    }
}
