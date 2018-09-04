package com.lujie.mongo_demo;

import com.mongodb.DBObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "student")
public class User implements Serializable{
    @Id
    private String _id;
    @Indexed
    private String username;
    private String password;
    private String sex;
    @DBRef
    private List<Address> addList;
    @PersistenceConstructor
    public User(String _id, String username, String password, String sex, List<Address> addList) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.addList = addList;
    }

    public User(String username, String password, String sex, List<Address> addList) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.addList = addList;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Address> getAddList() {
        return addList;
    }

    public void setAddList(List<Address> addList) {
        this.addList = addList;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", addList=" + addList +
                '}';
    }
}
