package com.lujie.demo.service;

import com.lujie.demo.bean.PageBean;
import com.lujie.demo.dao.ScoreDao;
import com.lujie.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
/***
 * 学生信息服务层
 * @author 卢杰
 * 时间：2018.8.3
 */
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ScoreDao scoreDao;
    /**
     * 获得所有的学生信息
     * @param pageBean  分页对象
     * @return  学生数据集合
     */
    public List queryAllStudent(PageBean pageBean,Map map){
       return studentDao.queryAllStudent(pageBean,map);
    }

    //添加学生
    public void insertStudent(Map map){
        studentDao.insertStudent(map);
    }

    /**
     * 修改学生信息
     * @param stringObjectMap Map对象
     */
    public void updateStudent(Map<String,Object> stringObjectMap){
       studentDao.updateStudent(stringObjectMap);
    }



    /**
     *  删除学生信息
     * @param sId   需要删除学生的编号
     */
    @Transactional
    public void deleteByIdStudent(Integer sId){
        //删除对应学生
        studentDao.deleteByIdStudent(sId);
        //删除该学生的成绩
        scoreDao.deleteBySIdScore(sId);
    }

    /**
     *  通过学生编号获得学生信息
     * @param sId
     * @return Map对象
     */
    public Map queryByIdStudent(Integer sId){
       return studentDao.queryByIdStudent(sId);
    }
    //获得学生总数

    public Long queryStudentCount(){
        return studentDao.queryStudentCount();
    }
}
