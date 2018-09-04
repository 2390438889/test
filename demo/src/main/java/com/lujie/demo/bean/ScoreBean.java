package com.lujie.demo.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 学生成绩实体类
 * @author 卢杰
 * 时间:2018.8.2
 */
@Component
public class ScoreBean implements Serializable {
    //学生编号
    private StudentBean sId;
    //考试编号
    private ExamBean eId;
    //考试成绩
    private Integer scScore;

    public ScoreBean() {
    }

    public ScoreBean(StudentBean sId, ExamBean eId, Integer scScore) {
        this.sId = sId;
        this.eId = eId;
        this.scScore = scScore;
    }

    public StudentBean getsId() {
        return sId;
    }

    public void setsId(StudentBean sId) {
        this.sId = sId;
    }

    public ExamBean geteId() {
        return eId;
    }

    public void seteId(ExamBean eId) {
        this.eId = eId;
    }

    public Integer getScScore() {
        return scScore;
    }

    public void setScScore(Integer scScore) {
        this.scScore = scScore;
    }

    @Override
    public String toString() {
        return "ScoreBean{" +
                "sId=" + sId +
                ", eId=" + eId +
                ", scScore=" + scScore +
                '}';
    }
}
