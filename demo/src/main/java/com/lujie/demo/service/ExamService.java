package com.lujie.demo.service;

import com.lujie.demo.bean.ExamBean;
import com.lujie.demo.bean.PageBean;
import com.lujie.demo.dao.ExamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
/**
 * 考试信息服务层交互
 * @author 卢杰
 * 时间:2018.8.3
 */
public class ExamService {
    @Autowired
    private ExamDao examDao;

    /**
     * 获得所有的考试信息
     * @param pageBean 分页信息
     * @return  考试信息集合
     */
    public List queryAllExam(PageBean pageBean){
        return examDao.queryAllExam(pageBean);
    }

    /**
     * 通过编号获得考试信息
     * @param eId   需要查询的考试编号
     * @return
     */
    public Map queryByIdExam(Integer eId){
        return examDao.queryByIdExam(eId);
    }

    /**
     * 获得考试信息总数
     * @return
     */
    public Long queryExamCount(){
        return examDao.queryExamCount();
    }

    /**
     * 添加考试
     * @param examBean  考试信息对象
     */
    public void insertExam(ExamBean examBean){
        examDao.insertExam(examBean);
    }

    /**
     * 删除考试
     * @param eId 需要删除的考试的编号
     */
    public void deleteExam(Integer eId){
       examDao.deleteExam(eId);
    }

    /**
     * 修改考试信息
     * @param maps  需要修改的考试信息的Map
     */
    public void updateByIdExam(Map<String,Object> maps){
        examDao.updateByIdExam(maps);
    }

}
