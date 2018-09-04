package com.lujie.fmrmsystem.Service.impl;

import com.lujie.fmrmsystem.Service.ExcelFileService;
import com.lujie.fmrmsystem.pojo.Log;
import com.lujie.fmrmsystem.util.ExcelUtil;
import com.lujie.fmrmsystem.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class ExcelFileServviceImpl implements ExcelFileService{
    //备份文件目录
    @Value("${excel.old}")
    private String excelOld;
    //源文件目录
    @Value("${excel.new}")
    private String excelNew;

    //将源文件目录下的文件备份到备份文件目录中
    public void fileBak(File fileOld,File fileNew) throws Exception {

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        //创建新的文件夹，名称为今日日期
        File fileBak=new File(fileOld,simpleDateFormat.format(new Date()));
        //如果创建的的新文件夹不存在则创建文件夹
        if(!(fileBak.isFile()|| fileBak.exists())){
            fileBak.mkdir();
        }
        //遍历源目录中的所有文件
        for(File file:fileNew.listFiles()){
            //将源目录中的文件复制到备份目录中
            if(file.getName().endsWith(".xls")||file.getName().endsWith(".xlsx")){
                FileUtil.CopyFile(file,new File(fileBak,file.getName()));
            }

        }


    }

    //读取源目录下的所有文件，将其中的xls或xlsx文件中的数据读取出来
    public void fileToSql(File fileNew){
        File[] files=fileNew.listFiles();
        ExcelUtil excelUtil;
        for(int i=0;i<files.length;i++){
            if(files[i].getName().endsWith(".xls")||files[i].getName().endsWith(".xlsx")) {
              //  System.out.println("------------------------------" + files[i].getName() + "------------------------------");
                excelUtil = new ExcelUtil(files[i], Log.class);
                excelUtil.readStart();
                files[i].delete();
            }
        }
    }

    @Override
    public void uploadExcel(){
        File fileNew=new File(excelNew);
        File fileOld=new File(excelOld);
        //备份文件
        try {
            fileBak(fileOld,fileNew);
            fileToSql(fileNew);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
