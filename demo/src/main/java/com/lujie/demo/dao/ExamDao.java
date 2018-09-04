package com.lujie.demo.dao;

import com.lujie.demo.bean.ExamBean;
import com.lujie.demo.bean.PageBean;
import com.lujie.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
/**
 * 考试信息与数据库交互
 * @author 卢杰
 * 时间:2018.8.2
 */
public class ExamDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PageDao pageDao;
    private String resultField="e_id,e_time,e_info";
    /**
     * 获得所有的考试信息
     * @param pageBean 分页信息
     * @return  考试信息集合
     */
    public List queryAllExam(PageBean pageBean){
        //构建sql语句
        String sql="select "+resultField+" from t_exam";
        return pageDao.queryForMysql(pageBean,sql);
    }

    /**
     * 通过编号获得考试信息
     * @param eId   需要查询的考试编号
     * @return
     */
    public Map queryByIdExam(Integer eId){
        Map map=null;
        //构建SQL语句
        String sql="select "+resultField+" from t_exam where e_id="+eId;
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 获得考试信息总数
     * @return
     */
    public Long queryExamCount(){
        //构建sql语句
        String sql="select count(1) as total from t_exam";
        return (Long)jdbcTemplate.queryForMap(sql).get("total");
    }

    /**
     * 添加考试
     * @param examBean  考试信息对象
     */
    public void insertExam(ExamBean examBean){
        //构建sql语句
        String sql="insert into t_exam(e_time,e_info) values('"+ Util.dateFormat(examBean.geteTime())+"','"+ examBean.geteInfo()+"')";
        jdbcTemplate.execute(sql);
    }

    /**
     * 删除考试
     * @param eId 需要删除的考试的编号
     */
    public void deleteExam(Integer eId){
        //构建sql语句
        String sql="delete from t_exam where e_id="+eId;
        jdbcTemplate.execute(sql);
    }

    /**
     * 修改考试信息
     * @param maps  需要修改的考试信息的Map
     */
    public void updateByIdExam(Map<String,Object> maps){
        //构建sql语句
        StringBuffer sql=new StringBuffer("update t_exam set ");
        for(Map.Entry<String,Object> map:maps.entrySet()){
            //如果值类型为整型则不加单引号
            if(map.getValue() instanceof Integer){
                sql.append(map.getKey()+"="+map.getValue()+",");
            }else{
                sql.append(map.getKey()+"='"+map.getValue()+"',");
            }

        }
        //去除最后的逗号
        sql.delete(sql.length()-1,sql.length());
        jdbcTemplate.execute(sql.toString());
    }

}
