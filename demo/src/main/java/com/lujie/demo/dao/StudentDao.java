package com.lujie.demo.dao;

import com.lujie.demo.bean.PageBean;
import com.lujie.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
/**
 * 学生信息数据库操作
 * @author 卢杰
 * 时间:2018.8.2
 */
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PageDao pageDao;
    private String resultField="s_id ,s_name ,s_sex ," +
            "s_birth_time ,s_in_time," +
            "s_like,s_info";

    /**
     * 获得所有的学生信息
     * @param pageBean  分页对象
     * @return  学生数据集合
     */
    public List queryAllStudent(PageBean pageBean,Map where){
        //构建sql语句
        StringBuffer sql=new StringBuffer("select "+resultField+" from t_student ");
        //如果
        if(where.entrySet().size()>0){
            sql.append("where ");
        }

        for(Object o:where.entrySet()){
            Map.Entry entry= (Map.Entry) o;
            sql.append(entry.getKey()+" like '%"+entry.getValue()+"%' and ");
        }
        int index=sql.lastIndexOf("and");
        if(index>0){
            sql.delete(index,sql.length());
        }

        return pageDao.queryForMysql(pageBean,sql.toString());
    }

    //添加学生
    public void insertStudent(Map map){

        StringBuffer field=new StringBuffer();
        StringBuffer value=new StringBuffer();
        StringBuffer sql=new StringBuffer();
        for(Object o:map.entrySet()){

            Map.Entry entry= (Map.Entry) o;
            Object val=entry.getValue();
            Object key=entry.getKey();
            if(val instanceof Date){
                field.append(key+",");
                value.append("'"+Util.dateFormat((Date)entry.getValue())+"',");
            }else if(val instanceof Integer){
                if(val!=null){
                    field.append(key+",");
                    value.append(entry.getValue()+",");
                }
            }else if(key instanceof String && key.toString().startsWith("sLike") || key.equals("s_id")){

            }else{
                field.append(key+",");
                value.append("'"+entry.getValue()+"',");
            }

        }
        //去除最后的逗号
        field.delete(field.length()-1,field.length());
        value.delete(value.length()-1,value.length());
        //构建sql语句
        sql.append("insert into t_student(");
        sql.append(field);
        sql.append(") values(");
        sql.append(value);
        sql.append(")");
        jdbcTemplate.execute(sql.toString());
    }

    /**
     * 修改学生信息
     * @param stringObjectMap Map对象
     */
    public void updateStudent(Map<String,Object> stringObjectMap){
        //构建sql语句
        StringBuffer sql=new StringBuffer("update t_student set ");
        for(Map.Entry<String,Object> map:stringObjectMap.entrySet()){
            if(map.getValue() instanceof Integer){
                sql.append(map.getKey()+"="+map.getValue()+",");
            }else{
                sql.append(map.getKey()+"='"+map.getValue()+"',");
            }

        }
        //去除最后的逗号
        sql.delete(sql.length()-1,sql.length());
        sql.append(" where s_id="+stringObjectMap.get("s_id"));
        //执行SQL语句
        jdbcTemplate.execute(sql.toString());

    }



    /**
     *  删除学生信息
     * @param sId   需要删除学生的编号
     */
    public void deleteByIdStudent(Integer sId){
        //构建sql语句
        String sql="delete from t_student where s_id="+sId;
        jdbcTemplate.execute(sql);
    }

    /**
     *  通过学生编号获得学生信息
     * @param sId
     * @return Map对象
     */
    public Map queryByIdStudent(Integer sId){
        //构建sql语句
        String sql="select "+resultField+" from t_student where s_id="+sId;
       return jdbcTemplate.queryForMap(sql);
    }
    //获得学生总数

    public Long queryStudentCount(){
        //构建sql语句
        String sql="select count(1) as total from t_student";
        Map map=jdbcTemplate.queryForMap(sql);
        return (Long)map.get("total");
    }
}
