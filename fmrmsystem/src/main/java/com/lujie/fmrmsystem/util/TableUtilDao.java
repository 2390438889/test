package com.lujie.fmrmsystem.util;

import java.util.List;
import java.util.Map;


public interface TableUtilDao<T> {



    //创建表
    public String createTable();

    //插入数据
    public String insert(T obj);

    //修改数据
    public String update(T obj) throws Exception;

    //根据编号删除数据
    public String delete(Integer id);

    //查询全部
    public List<T> findAll();

    //根据指定条件查询

    public String findByWhere(Map param);

    //原始查询
    public String queryList(String sql);

    //根据指定条件模糊查询
    public String findByLike(Map param);
}
