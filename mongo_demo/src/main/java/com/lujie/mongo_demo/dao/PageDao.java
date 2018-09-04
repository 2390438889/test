package com.lujie.mongo_demo.dao;

import com.lujie.mongo_demo.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author:卢杰
 * 分页实体类
 * 时间：2018-8-16
 */
@Repository
public class PageDao implements Serializable {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * mongoDB分页处理
     * @param query 分页前筛选
     * @param clazz 所属类
     * @param pageBean  分页对象
     * @return  数据集合
     */
    public List pageForMongoDB(Query query, Class clazz, PageBean pageBean){
        return mongoTemplate.find(query.skip(pageBean.getPage()).limit(pageBean.getLimit()),clazz);
    }
}
