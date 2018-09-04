package com.lujie.fmrmsystem;

import com.lujie.fmrmsystem.pojo.Log;
import com.lujie.fmrmsystem.pojo.Menu;
import com.lujie.fmrmsystem.util.TableUtil;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;


@Component
public class test extends TableUtil<Menu>{
    @Test
    public void set(){
        ExcelUtil excelUtil=new ExcelUtil(new File("D:\\内网通文件\\Creep丶\\log0822-0828.xlsx"),Log.class);
        excelUtil.readStart();
    }

    @Test
    public void test(){
        Menu menu=new Menu("首页","显示主页信息","/","#1111",null);
        // menu.setCmId(1);
            System.out.println(this.insert(menu));
    }

/*    public static void main(String[] args){
        Menu menu=new Menu("首页","显示主页信息","/","#1111",null);
        // menu.setCmId(1);
        System.out.println((new test()).insert(menu));
    }*/



}
