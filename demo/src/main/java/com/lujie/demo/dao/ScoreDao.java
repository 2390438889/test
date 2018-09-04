package com.lujie.demo.dao;

import com.lujie.demo.bean.PageBean;
import com.lujie.demo.bean.ScoreBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 学生成绩与数据库交互
 * @author 卢杰
 * 时间：2018.8.2
 */
@Repository
public class ScoreDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PageDao pageDao;
    private final String resultField="s_name,e_info,e_time,sc.*";

    /**
     * 通过学生编号获得成绩
     * @param pageBean  分页对象
     * @param sId       需要查询的学生的编号
     * @return          学生成绩集合
     */
    public List queryBySIdScore(PageBean pageBean,Integer sId){
        List list=null;
        //构建Sql语句
        String sql="select "+resultField+" from t_student s left join t_score sc on sc.s_id=s.s_id right join t_exam e " +
                "on e.e_id=sc.e_id where s.s_id="+sId;
        list=pageDao.queryForMysql(pageBean,sql);
        return list;
    }

    /**
     * 通过考试编号获得成绩
     * @param pageBean 分页信息
     * @param eId      需要查询的考试的编号
     * @return         学生成绩集合
     */
    public List queryByEIdScore(PageBean pageBean,Integer eId){
        List list=null;
        //构建Sql语句
        String sql="select "+resultField+" from t_student s left join t_score sc on sc.s_id=s.s_id right join t_exam e " +
                "on e.e_id=sc.e_id where e.e_id="+eId;
        list=pageDao.queryForMysql(pageBean,sql);
        return list;
    }

    /**
     *  添加考试信息
     * @param scoreBean 需要添加的分数的信息
     */
    public void insertScore(ScoreBean scoreBean){
        //构建sql语句
        String sql="insert into t_score (s_id,e_id,sc_score) values("+scoreBean.getsId().getsId()+"," +
                ""+scoreBean.geteId().geteId()+","+scoreBean.getScScore()+")";
        jdbcTemplate.execute(sql);
    }

    /**
     * 修改考试信息
     * @param scoreBean 需要修改的考试的信息
     */
    public void updateScore(ScoreBean scoreBean){
        //构建sql语句
        String sql="update t_score set sc_score="+scoreBean.getScScore()+" where s_id="+scoreBean.getsId().getsId()+"" +
                " and e_id="+scoreBean.geteId().geteId();
        jdbcTemplate.execute(sql);

    }

    /**
     * 获得每场考试的及格率,和平均分
     * @return
     */
    public List<Map<String,Object>> queryByPassRateScore(){
        //构建sql语句
        String sql="select e.e_info as e_name,avg(sc.sc_score) as avg,sum(if(sc.sc_score>=60,1,0))" +
                "/count(1) as pass_rate from t_score sc right join t_exam e on sc.e_id=e.e_id group " +
                "by sc.e_id\n";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 清空考试成绩
     * @param where 外加的条件查询,若其值为null或者为空字符串则为清空考试成绩
     */
    public void deleteScore(String where){
        if(where==null || where.trim().equals("")){
            jdbcTemplate.execute("delete from t_score");
        }else{
            jdbcTemplate.execute("delete from t_score where "+where);
        }

    }

    /**
     * 通过学生编号删除考试成绩
     * @param s_id  需要删除的学生编号
     */
    public void deleteBySIdScore(Integer s_id){
        deleteScore("s_id="+s_id);
    }

    /**
     * 通过考试编号删除考试成绩
     * @param e_id  需要删除的考试编号
     */
    public void deleteByEIdScore(Integer e_id){
        deleteScore("e_id="+e_id);
    }




}
