package com.lujie.demo;

import com.lujie.demo.bean.ExamBean;
import com.lujie.demo.bean.PageBean;
import com.lujie.demo.bean.ScoreBean;
import com.lujie.demo.bean.StudentBean;
import com.lujie.demo.dao.ExamDao;
import com.lujie.demo.dao.ScoreDao;
import com.lujie.demo.dao.StudentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 测试数据库连接
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ScoreDao scoreDao;
    @Test
    /**
     * 添加学生成绩
     */
    public void insertScoreTest(){

        scoreDao.insertScore(new ScoreBean(new StudentBean(2),new ExamBean(2),99));
    }

    @Test
    /**
     * 通过学生编号删除考试成绩
     */
    public void deleteBySIdScoreTest(){
        scoreDao.deleteBySIdScore(7);

    }

    @Test
    /**
     * 随机插入成绩
     */
    public void randomInsertScoreTest(){
        Random random=new Random();
       for(Object o:studentDao.queryAllStudent(new PageBean(1,10),new HashMap())){
            Map stuMap=(Map)o;
            for(Object e:examDao.queryAllExam(new PageBean(1,10))){
                Map examMap=(Map) e;
                scoreDao.insertScore(new ScoreBean(new StudentBean((int)stuMap.get("s_id")),new ExamBean((int)examMap.get("e_id")),random.nextInt(100)));
            }
        }
    }

    @Test
    /**
     * 获得平均分和及格率
     */
    public void queryRateTest(){
        System.out.println(scoreDao.queryByPassRateScore());
    }

    @Test
    /**
     * 通过学生编号获得学生成绩
     */
    public void queryBySIdScoreTest(){
        System.out.println(scoreDao.queryBySIdScore(new PageBean(1,5),3));
    }

    @Test
    /**
     * 通过考试编号获得学生成绩
     */
    public void queryByIdScoreTest(){
        System.out.println(scoreDao.queryByEIdScore(new PageBean(1,5),2));
    }

    @Test
    /**
     * 修改学生成绩
     */
    public void updateScoreTest(){
        scoreDao.updateScore(new ScoreBean(new StudentBean(2),new ExamBean(2),98));
    }

    /**
     * 测试数据库连接是否有效
     */
    @Test
    public void getConnection(){
       System.out.println(studentDao.queryStudentCount());
    }

    @Test
    /**
     * 获得所有的考试数据
     */
    public void queryAllExamTest(){
        PageBean pageBean=new PageBean();
        pageBean.setPage(1);
        pageBean.setLimit(5);
        System.out.println(examDao.queryAllExam(pageBean));
    }

    /**
     * 获得所有考试数量
     */
    @Test
    public void queryExamCount(){
        System.out.println(examDao.queryExamCount());
    }

    @Test
    /**
     * 通过编号获得考试数据
     */
    public void queryByIdExamTest(){
        Map map=examDao.queryByIdExam(1);
        System.out.println(map);
        map.put("e_info","期末考试");
        examDao.updateByIdExam(map);

    }

    @Test
    /**
     * 通过编号删除考试数据
     */
    public void deleteByIdExamTest(){
        examDao.deleteExam(1);
    }

    @Test
    /**
     * 插入一条考试数据
     */
    public void insertExamTest(){
        ExamBean examBean=new ExamBean(new Date("2018/6/18"),"期中考试");
        for(int i=0;i<3;i++){
            examDao.insertExam(examBean);
        }

    }

    @Test
    /**
     * 获得所有的学生数据
     */
    public void queryAllStudentTest(){
        PageBean pageBean=new PageBean();
        pageBean.setPage(1);
        System.out.println(studentDao.queryAllStudent(pageBean,new HashMap()));
    }
    @Test
    /**
     * 插入一条学生数据
     */
    public void insertStudentTest(){
       // StudentBean stu=new StudentBean("卢杰","F",new Date("1997/11/24"),new Date(),"乒乓球","ok");
        Map map=new HashMap();
        map.put("s_name","卢杰");
        map.put("s_sex","F");
        map.put("s_birth_time",new Date("1997/11/24"));
        map.put("s_in_time",new Date());
        map.put("s_like","吃鸡");
        map.put("s_info","自我简介");

            studentDao.insertStudent(map);

    }


    @Test
    /**
     * 通过编号获得学生数据
     */
    public void queryByIdStudentTest(){
        Map map=studentDao.queryByIdStudent(3);
        System.out.println(map);
        map.put("s_name","周巍");
        studentDao.updateStudent(map);

    }

    @Test
    /**
     * 通过编号删除学生数据
     */
    public void deleteByIdTest(){
        studentDao.deleteByIdStudent(4);
    }
}
