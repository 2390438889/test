package com.lujie.fmrmsystem.util;

import com.lujie.fmrmsystem.annotation.ExcelCell;
import com.lujie.fmrmsystem.annotation.ExcelSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.naming.event.ObjectChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class ExcelWrite {
    protected File excelFile;
    protected Class clazz;
    private Workbook workbook;
    private int sheets;
    private Map<String,Field> fieldsMap;
    private Map<Field,String> cellsMap;
    private Map<Integer,String> numsMap;
    private Map<Field,String> methods;

    public ExcelWrite(File file,Class clazz){
        this.excelFile=file;
        this.clazz=clazz;
        init();
    }

    //初始化
    private void init(){
        ExcelSheet excelSheet= (ExcelSheet) clazz.getAnnotation(ExcelSheet.class);
        ExcelCell excelCell;
        if(excelSheet!=null){
            fieldsMap=new HashMap<>();
            cellsMap=new HashMap<>();
            numsMap=new HashMap<>();
            methods=new HashMap<>();
            sheets=excelSheet.value();
            int i=0;
            for(Field field:clazz.getDeclaredFields()){
                excelCell=field.getAnnotation(ExcelCell.class);
                if(excelCell!=null){
                    fieldsMap.put(excelCell.colName(),field);
                    cellsMap.put(field,excelCell.colName());
                    methods.put(field,field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
                }
            }
            try  {
                FileInputStream fis = new FileInputStream(excelFile);
                //初始化Excel表对象
                if(excelFile.getName().endsWith(".xls")){
                    workbook=new HSSFWorkbook(fis);
                }else if(excelFile.getName().endsWith(".xlsx")){
                    workbook=new XSSFWorkbook(fis);
                }else{
                    throw new Exception("文件格式有误，只能为.xls/.xlsx文件");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //初始化单元格序号
            Sheet sheet=workbook.getSheetAt(sheets);
            Row row=sheet.getRow(0);
            for(int j=0;j<row.getLastCellNum();j++){
                Cell cell=row.getCell(j);
                numsMap.put(j,cell.getStringCellValue());
            }



        }
    }
    public abstract void toSql(List list);

    public abstract void toExcel(List list);

    //读取Excel中的数据
    public List readExcel() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List list=new ArrayList();
        Method method;
        Row row;
        Cell cell;
        Sheet sheet=workbook.getSheetAt(sheets);
        for(int k=1;k<sheet.getLastRowNum();k++){
            row=sheet.getRow(k);
            Object obj=clazz.newInstance();
            for(int j=0;j<row.getLastCellNum();j++){
                //获得该序号列名
                String cellName=numsMap.get(j);
                cell=row.getCell(j);
                //获得列名对应的字段
                Field field=fieldsMap.get(cellName);
                if(field!=null){
                    //将值存到对应的字段中
                    method=clazz.getDeclaredMethod("set"+methods.get(field),new Class[]{field.getType()});
                    //如果字段类型为Date类型
                    if(field.getType().getName().endsWith("Date")){
                        method.invoke(obj,parseDate(cell.getStringCellValue()));
                    }else if(field.getType()==String.class){
                        method.invoke(obj,cell.getStringCellValue());
                    }else{
                        method.invoke(obj,field.getType().cast(cell.getNumericCellValue()));
                    }
                }

            }
            list.add(obj);
        }
        return list;
    }

    public abstract Date parseDate(String value);

    public void readStart(){
        try {
            List list=readExcel();
            toSql(list);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
