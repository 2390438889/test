package com.lujie.fmrmsystem;

import com.lujie.fmrmsystem.pojo.Log;
import com.lujie.fmrmsystem.pojo.Menu;
import com.lujie.fmrmsystem.util.ExcelWrite;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExcelUtil extends ExcelWrite{

    public ExcelUtil(File file, Class clazz) {
        super(file, clazz);
    }

    @Override
    public void toSql(List list) {
        for(Object obj:list){
            System.out.println(obj);
        }
    }

    @Override
    public void toExcel(List list) {

    }

    @Override
    public Date parseDate(String value) {
        return new Date(value.replace("-","/"));
    }
}
