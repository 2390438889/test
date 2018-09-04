package com.lujie.demo.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息实体类
 * @author 卢杰
 * 时间：2018.8.2
 */
@Component
public class StudentBean implements Serializable {
    //学生编号
    private Integer sId;
    //学生姓名
    private String sName;
    //学生性别
    private String sSex;
    //出生年月
    private Date sBirthTime;
    //入学日期
    private Date sInTime;
    //兴趣爱好
    private String sLike;
    //自我评价
    private String sInfo;

    public StudentBean() { }

    public StudentBean(Integer sId) {
        this.sId = sId;
    }

    public StudentBean(String sName, String sSex, Date sBirthTime, Date sInTime, String sLike, String sInfo) {
        this.sName = sName;
        this.sSex = sSex;
        this.sBirthTime = sBirthTime;
        this.sInTime = sInTime;
        this.sLike = sLike;
        this.sInfo = sInfo;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public Date getsBirthTime() {
        return sBirthTime;
    }

    public void setsBirthTime(Date sBirthTime) {
        this.sBirthTime = sBirthTime;
    }

    public Date getsInTime() {
        return sInTime;
    }

    public void setsInTime(Date sInTime) {
        this.sInTime = sInTime;
    }

    public String getsLike() {
        return sLike;
    }

    public void setsLike(String sLike) {
        this.sLike = sLike;
    }

    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", sSex='" + sSex + '\'' +
                ", sBirthTime=" + sBirthTime +
                ", sInTime=" + sInTime +
                ", sLike='" + sLike + '\'' +
                ", sInfo='" + sInfo + '\'' +
                '}';
    }
}
