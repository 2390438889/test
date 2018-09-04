package com.lujie.fmrmsystem.pojo;

import com.lujie.fmrmsystem.annotation.ExcelCell;
import com.lujie.fmrmsystem.annotation.ExcelSheet;

import java.util.Date;
@ExcelSheet(0)
public class Log {
    //操作时间：
    @ExcelCell(colName = "操作时间")
    private Date operateTime;
    //IP/MAC：
    @ExcelCell(colName = "IP/MAC")
    private String ipOrMac;
    //类别：
    @ExcelCell(colName = "类别")
    private String category;
    //操作行为：
    @ExcelCell(colName = "操作行为")
    private String behavior;
    //客体：
    @ExcelCell(colName = "客体")
    private String object;
    //操作结果：
    @ExcelCell(colName = "操作结果")
    private String operateResult;
    //详情：
    @ExcelCell(colName = "详情")
    private String detail;

    public Log(Date operateTime, String ipOrMac, String category, String behavior, String object, String operateResult, String detail) {
        this.operateTime = operateTime;
        this.ipOrMac = ipOrMac;
        this.category = category;
        this.behavior = behavior;
        this.object = object;
        this.operateResult = operateResult;
        this.detail = detail;
    }

    public Log() {
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getIpOrMac() {
        return ipOrMac;
    }

    public void setIpOrMac(String ipOrMac) {
        this.ipOrMac = ipOrMac;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Log{" +
                "operateTime=" + operateTime +
                ", ipOrMac='" + ipOrMac + '\'' +
                ", category='" + category + '\'' +
                ", behavior='" + behavior + '\'' +
                ", object='" + object + '\'' +
                ", operateResult='" + operateResult + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
