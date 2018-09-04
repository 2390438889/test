package com.lujie.demo.dao;

import com.lujie.demo.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分页数据库层实现
 * @author 卢杰
 * 时间:2018.8.2
 */
@Repository
public class PageDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 分页查询mysql数据库
     * @param pageBean  数据分页实体类
     * @param sql       sql语句
     * @return
     */
    public List queryForMysql(PageBean pageBean,String sql){
        List list=null;
        //获得当前页码数
        Integer page=pageBean.getPage();
        //获得每页数据条数
        Integer pageNum=pageBean.getLimit();
        //起始索引
        Integer start=(page-1)*pageNum;
        //构建sql语句

        String sqls=sql+" limit "+start+" , "+pageNum;
        //执行sql语句
        list=jdbcTemplate.queryForList(sqls);

        return list;
    }

    /**
     * 分页查询Oracle数据库
     * @param pageBean  分页对象
     * @param field     需要返回的字段名
     * @param table     查询的表名
     * @param where     查询条件
     * @param orderby   排序条件
     * @return
     */
    public List queryForOracle(PageBean pageBean,String field,String table,String where,String orderby){
        List list=null;
        //获得当前页码数
        Integer page=pageBean.getPage();
        //获得每页数据条数
        Integer pageNum=pageBean.getLimit();
        //起始索引
        Integer start=(page-1)*pageNum;
        //获得结束索引
        Integer end=page*pageNum;
        //构建sql语句
        StringBuffer sqls=new StringBuffer();
        sqls.append("select ");
        sqls.append(field);
        sqls.append("  ROWNUM RN from ");
        sqls.append(table);
        sqls.append(where);
        //如果条件查询为空字符串则不加and
        if(where!=null||where.trim().equals("")){
            sqls.append(" and");
        }
        sqls.append(" RN>"+start+" and RN<="+end);
        sqls.append(orderby);
        list=jdbcTemplate.queryForList(sqls.toString());
        return list;
    }
}
