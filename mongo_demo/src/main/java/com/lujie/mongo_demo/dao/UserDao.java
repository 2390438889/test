package com.lujie.mongo_demo.dao;

import com.lujie.mongo_demo.pojo.PageBean;
import com.lujie.mongo_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author 卢杰
 * 用户信息操作数据层
 */
@Repository
public class UserDao implements Serializable{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PageDao pageDao;

    /**
     * 登陆操作
     * @param user 登陆的用户信息
     * @return  用户对象
     */
    public User login(User user){
        User u=null;
        u=mongoTemplate.findOne(Query.query(Criteria.where("u_name").is(user.getU_name()).and("u_pwd").is(user.getU_pwd())),User.class);
        return u;
    }

    /**
     * 注册操作
     * @param user  注册用户信息
     */
    public void regUser(User user){
        mongoTemplate.insert(user,"student");
    }

    /**
     * 通过编号获得用户对象
     * @param id    需要获得的编号
     * @return      用户对象
     */
    public User userFindById(String id){
        User u=null;
        u=mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)),User.class);
        return u;
    }

    /**
     * 修改用户信息
     * @param user  需要修改的用户对象
     */
    public void userUpdate(User user){
        mongoTemplate.save(user);
    }

    /**
     * 通过编号删除信息
     * @param id
     */
    public void userDelete(String id){
        mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)),User.class);
    }

    /**
     * 分页查询所有用户信息
     * @param pageBean  分页信息
     * @return  用户集合
     */
    public List userFindList(PageBean pageBean){
        Query query=Query.query(Criteria.where("u_name").exists(true).ne(null).and("u_pwd").exists(true).ne(null));

        return pageDao.pageForMongoDB(query,User.class,pageBean);
    }


}
