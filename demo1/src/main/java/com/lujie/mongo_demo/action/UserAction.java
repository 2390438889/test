package com.lujie.mongo_demo.action;

import com.lujie.mongo_demo.pojo.PageBean;
import com.lujie.mongo_demo.pojo.User;
import com.lujie.mongo_demo.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author 卢杰
 * 功能：用户控制层实现
 * 时间：2018-8-17
 */
@Controller
@RequestMapping("/user")
public class UserAction implements Serializable {
    @Autowired
    private UserService userService;

    /**
     * 登陆功能实现
     * @param user  登陆的用户
     * @parm hs session对象
     * @return  登陆成功后的用户
     */
    @RequestMapping("/login")
    @ResponseBody
    public User login(User user, HttpSession hs){
        System.out.println("----------登陆------------");
        User u=userService.login(user);
        System.out.println(u);
        hs.setAttribute("user",u);
        return u;

    }

    /**
     * 用户列表
     * @param pageBean  分页对象
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageBean userList(PageBean pageBean){
        System.out.println("----------用户列表------------");

        List list=null;
        list=userService.userFindList(pageBean);
        pageBean.setCode(0);
        pageBean.setData(list);
        pageBean.setCount(userService.userFindCount());
        pageBean.setMsg("");
        return pageBean;
    }

    /**
     * 注册
     * @param user  需要注册的用户信息
     */
    @RequestMapping("/register")
    @ResponseBody
    public void userRegister(User user){
        System.out.println(user);
        if(user.getU_id()==null){
            userService.regUser(user);
        }else{
            userService.userUpdate(user);
        }

    }

    /**
     * 删除单个用户
     * @param id    需要删除的用户编号
     */
    @RequestMapping("/delete")
    @ResponseBody
    public void userDelete(@RequestParam String id){
        userService.userDelete(id);
    }

    /**
     * 获得当前登录对象
     * @param hs    session对象
     * @return
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public User userSession(HttpSession hs){
        return (User)hs.getAttribute("user");
    }

    /**
     * 注销操作
     * @param hs    session对象

     *
     */
    @RequestMapping("/removeLogin")
    @ResponseBody
    public void userRemoveLogin(HttpSession hs){
        hs.setAttribute("user",null);
    }

    @RequestMapping("/getList")
    @ResponseBody
    public void getArray(@RequestParam("users") String users){
        JSONArray jsonArray= JSONArray.fromObject(users);
        List<User> userlist= (List<User>) JSONArray.toCollection(jsonArray,User.class);
        System.out.println(userlist);

    }

}
