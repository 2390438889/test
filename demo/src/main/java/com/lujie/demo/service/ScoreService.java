package com.lujie.demo.service;

import com.lujie.demo.bean.PageBean;
import com.lujie.demo.bean.ScoreBean;
import com.lujie.demo.dao.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学生成绩信息服务层交互
 * @author  卢杰
 * 时间：2018.8.3
 */
@Service
public class ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    /**
     * 通过学生编号获得成绩
     * @param pageBean  分页对象
     * @param sId       需要查询的学生的编号
     * @return          学生成绩集合
     */
    public List queryBySIdScore(PageBean pageBean, Integer sId){
        return scoreDao.queryBySIdScore(pageBean,sId);
    }

    /**
     * 通过考试编号获得成绩
     * @param pageBean 分页信息
     * @param eId      需要查询的考试的编号
     * @return         学生成绩集合
     */
    public List queryByEIdScore(PageBean pageBean,Integer eId){
        return scoreDao.queryByEIdScore(pageBean,eId);
    }

    /**
     *  添加考试信息
     * @param scoreBean 需要添加的分数的信息
     */
    public void insertScore(ScoreBean scoreBean){
        scoreDao.insertScore(scoreBean);
    }

    /**
     * 修改考试信息
     * @param scoreBean 需要修改的考试的信息
     */
    /**
     * 获得每场考试的及格率,和平均分
     * @return
     */
    public List<Map<String,Object>> queryByPassRateScore(){
        return scoreDao.queryByPassRateScore();
    }

}
