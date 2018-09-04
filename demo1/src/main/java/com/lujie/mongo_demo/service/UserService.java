package com.lujie.mongo_demo.service;

import com.lujie.mongo_demo.dao.UserDao;
import com.lujie.mongo_demo.pojo.PageBean;
import com.lujie.mongo_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author:卢杰
 * 用户操作服务层
 * 时间:2018-8-16
 */
@Service
public class UserService implements Serializable{
    @Autowired
    private UserDao userDao;

    /**
     * 登陆操作
     * @param user 登陆的用户信息
     * @return  用户对象
     */
    public User login(User user){
        return userDao.login(user);
    }

    /**
     * 注册操作
     * @param user  注册用户信息
     */
    public void regUser(User user){
       userDao.regUser(user);
    }

    /**
     * 通过编号获得用户对象
     * @param id    需要获得的编号
     * @return      用户对象
     */
    public User userFindById(String id){
        return userDao.userFindById(id);
    }

    /**
     * 修改用户信息
     * @param user  需要修改的用户对象
     */
    public void userUpdate(User user){
        userDao.userUpdate(user);
    }

    /**
     * 通过编号删除信息
     * @param id
     */
    public void userDelete(String id){
       userDao.userDelete(id);
    }

    /**
     * 分页查询所有用户信息
     * @param pageBean  分页信息
     * @return  用户集合
     */
    public List userFindList(PageBean pageBean){
        pageBean.setPage((pageBean.getPage()-1)*pageBean.getLimit());
        return userDao.userFindList(pageBean);
    }

    /**
     * 获得用户总数
     */
    public Integer userFindCount(){
       return userDao.userFindCount();
    }


}
