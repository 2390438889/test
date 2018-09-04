package pojo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Demo {
    //解析指定类注解
    public static String orm(Class clazz){
        StringBuffer sql=new StringBuffer();
        //获取表名
        String table=((Table)clazz.getAnnotation(Table.class)).value();
        //如果为命名则取类名全小写
        if(table.trim().length()==0){
            table=table.toLowerCase();
        }
        //构建sql语句
        sql.append("create table "+table+"(\n");
        //遍历字段
        for(Field field:clazz.getDeclaredFields()){
           Column column= field.getAnnotation(Column.class);
           sql.append(column.name()+" "+column.type()+" "+column.where()+" "+column.comment()+",\n");
        }
        sql.delete(sql.length()-2,sql.length());
        sql.append("\n");
        sql.append(")");
        return sql.toString();
    }
    public static void main(String[] args){
        System.out.println(orm(Student.class));
    }
}
