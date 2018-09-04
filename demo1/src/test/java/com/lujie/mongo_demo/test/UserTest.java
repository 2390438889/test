package com.lujie.mongo_demo.test;

import com.lujie.mongo_demo.dao.UserDao;
import com.lujie.mongo_demo.pojo.Address;
import com.lujie.mongo_demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private HttpSession hs;
    @Test
    /**
     * 用户注册
     */
    public void reg(){
        User user=new User();
        user.setU_name("吕擎");
        user.setU_pwd("175111");
        List<Address> add_list=Arrays.asList(new Address[]{new Address(user.getU_addr_index().toString(),"湖南省株洲市攸县")
        ,new Address(user.getU_addr_index().toString(),"湖南省长沙市长沙县星沙汽车站附近")});
        user.setU_addr_list(add_list);
        userDao.regUser(user);
    }

    /**
     * 用户登录
     */
    @Test
    public void login(){
        List list=mongoTemplate.find(Query.query(Criteria.where("u_name").is("吕擎").and("u_pwd").is("175111")),User.class);
        System.out.println(list);
    }

    @Test
    public void count(){
        User user=new User("卢杰","240799",null);
        Query query=Query.query(Criteria.where("u_name").is("卢杰").and("u_pwd").is("240799").and("u_addr_list.add_status").is("1"));

    }
}
