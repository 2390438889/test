package com.lujie.fmrmsystem.util;

import java.io.File;
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
