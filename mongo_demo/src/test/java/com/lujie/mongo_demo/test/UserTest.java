package com.lujie.mongo_demo.test;

import com.lujie.mongo_demo.dao.UserDao;
import com.lujie.mongo_demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserDao userDao;
    @Test
    /**
     * 用户注册
     */
    public void reg(){
        User user=new User("admin","123456");
        userDao.regUser(user);
    }

    /**
     * 用户登录
     */
    @Test
    public void login(){
        System.out.println(userDao.login(new User("admin","123456")));
    }
}
