package com.lujie.demo.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试信息实体类
 * @author 卢杰
 * 时间：2018.8.2
 */
@Component
public class ExamBean implements Serializable {
    //考试编号
    private Integer eId;
    //考试时间
    private Date eTime;
    //考试内容
    private String eInfo;

    public ExamBean() {
    }

    public ExamBean(Integer eId) {
        this.eId = eId;
    }

    public ExamBean(Date eTime, String eInfo) {
        this.eTime = eTime;
        this.eInfo = eInfo;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public String geteInfo() {
        return eInfo;
    }

    public void seteInfo(String eInfo) {
        this.eInfo = eInfo;
    }

    @Override
    public String toString() {
        return "ExamBean{" +
                "eId=" + eId +
                ", eTime=" + eTime +
                ", eInfo='" + eInfo + '\'' +
                '}';
    }
}
