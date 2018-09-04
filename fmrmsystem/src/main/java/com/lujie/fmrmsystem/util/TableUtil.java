package com.lujie.fmrmsystem.util;

import com.lujie.fmrmsystem.annotation.Column;
import com.lujie.fmrmsystem.annotation.Id;
import com.lujie.fmrmsystem.annotation.Table;
import com.lujie.fmrmsystem.pojo.Menu;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableUtil<T> implements TableUtilDao<T> {
    private  Class<T> clazz;
    private  Map map;
    private String tableName;
    private String [] columns;
    private Field [] fieldName;
    private String [] methodGetName;
    protected JdbcTemplate jdbcTemplate;

    public TableUtil() {
        //获得泛型的类型
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();//获取当前new对象的泛型的父类类型
        this.clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        getMapResult();

    }


    //解析注解构建map对象
    private void getMapResult(){
        this.map=new HashMap();
        Map<Class,String> table=new HashMap();
        Map<Field,Column> column=new HashMap();
        //获得表名
        Table tableObj=(Table)clazz.getAnnotation(Table.class);
        if(tableObj==null){
            map=null;
            return;
        }
        this.tableName=tableObj.value();
        //如果表名为空则默认为类名全小写
        table.put(clazz,tableName.trim().length()==0?clazz.getSimpleName().toLowerCase():tableName);
        //获得列名
        for(Field field:clazz.getDeclaredFields()){
            Column columnObj=(Column)field.getAnnotation(Column.class);
            if(columnObj!=null){
                column.put(field,columnObj);
            }
        }
        this.map.put("table",table);
        this.map.put("column",column);
        Map<Field,Column> columnMap= (Map<Field, Column>) map.get("column");
        //提取列名，字段名，get方法名
        columns=new String[columnMap.size()];
        fieldName=new Field[columns.length];
        methodGetName=new String[columns.length];
        //将字段和列名提取出来
        int i=0;
        for(Map.Entry<Field,Column> entry:columnMap.entrySet()){
            columns[i]=entry.getValue().name();
            fieldName[i]=entry.getKey();
            methodGetName[i++]="get"+entry.getKey().getName().substring(0,1).toUpperCase()+entry.getKey().getName().substring(1);
        }



    }

    @Override
    public String createTable() {
        //构建sql语句
        if(map!=null){
            StringBuilder sql=new StringBuilder();
            String tableName= (String) ((Map) map.get("table")).get(clazz);
            sql.append("create table if not exists "+tableName+"(\n");
            //构建字段
            Field field;
            Column column;
            Map<Field,Column> columnsMap= (Map<Field, Column>) map.get("column");
            for(Map.Entry<Field,Column> entry:columnsMap.entrySet()){
                field=entry.getKey();
                column=entry.getValue();
                sql.append(column.name()+" ");
                sql.append(column.type()+" ");
                sql.append(column.other()+" ");
                sql.append("comment '"+column.comment()+"',\n");
            }
            sql.delete(sql.length()-2,sql.length());
            sql.append("\n)\n");
            Map columns= (Map) map.get("column");
            jdbcTemplate.execute(sql.toString());
            return sql.toString();
        }
        return null;
    }
    @Override
    public String insert(T obj){
        if(map!=null){
            //构建sql语句
            StringBuilder sql=new StringBuilder();
            sql.append("insert into "+tableName+"(");
            int i=0;
            //构建列名
            for(i=0;i<columns.length;i++){
                sql.append(columns[i]+",");
            }
            //去除最后的逗号
            sql.delete(sql.length()-1,sql.length());
            sql.append(") values (");
            //构建值
            Method method;
            Object value;
            for(i=0;i<methodGetName.length;i++){
                //构建get方法
                try {
                    method=clazz.getMethod(methodGetName[i]);
                    try {
                        value= (String) method.invoke(obj);
                        if(value instanceof String||value instanceof Date){
                            sql.append("'"+value+"',");
                        }else{
                            sql.append(value+",");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            //去除最后的逗号
            sql.delete(sql.length()-1,sql.length());
            sql.append(")");
            jdbcTemplate.update(sql.toString());
            return sql.toString();

        }
        return null;
    }

    @Override
    public String update(T obj) throws Exception {
        if(map!=null){
            //构建sql语句
            StringBuilder sql=new StringBuilder();
            sql.append("update "+tableName+" set ");
            String where = null;
            Method method;
            Object value;
            //构建set
            for(int i=0;i<columns.length;i++){
                try {
                    method=clazz.getMethod(methodGetName[i]);
                    value=method.invoke(obj);
                    if(fieldName[i].getAnnotation(Id.class)!=null){
                        where=" where "+columns[i]+"="+value;
                    }else{
                        if(value instanceof String || value instanceof Date){
                            sql.append(columns[i]+"='"+value+"',");
                        }else{
                            sql.append(columns[i]+"="+method.invoke(obj)+",");
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
            //去除最后的逗号
            sql.delete(sql.length()-1,sql.length());
            if(where==null){
                throw new Exception("该类没有加@id注解");
            }
            //拼接where
            sql.append(where);
            jdbcTemplate.update(sql.toString());
            return sql.toString();
        }
        return null;
    }
    @Override
    public String delete(Integer id){
        if(map!=null){
            for (int i=0;i<fieldName.length;i++){
                //找到id字段
                if(fieldName[i].getAnnotation(Id.class)!=null){
                    //构建sql语句
                    String sql="delete from "+tableName+" where "+columns[i]+"="+id;
                    jdbcTemplate.update(sql);
                    return sql;
                }
            }
            return null;

        }
        return null;
    }
    @Override
    public List<T> findAll() {
        if(map!=null){
            //构建sql语句
            StringBuilder sql=new StringBuilder();
            sql.append("select * from "+tableName);

            return jdbcTemplate.queryForList(sql.toString(),clazz);
        }
        return null;
    }

    @Override
    public String findByWhere(Map param) {
        return null;
    }

    @Override
    public String queryList(String sql) {
        return sql;
    }

    @Override
    public String findByLike(Map param) {
        return null;
    }



    //创建表



}
