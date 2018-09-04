package com.lujie.mongo_demo.action;

import com.lujie.mongo_demo.pojo.User;
import com.lujie.mongo_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 卢杰
 * 功能：页面跳转
 * 时间：2018-8-17
 */
@Controller
public class SystemManager {
    @Autowired
    private UserService userService;
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        System.out.println("---------进入首页---------");
        return "index";
    }

    /**
     * 跳转到登陆界面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 跳转到注册界面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 跳转到修改界面
     */
    @RequestMapping("/update")
    public String update(@RequestParam String id, Model model){

        model.addAttribute("user",userService.userFindById(id));
        if(id==null){
            return "register";
        }
        return "update";
    }

    /**
     * 跳转到用户列表
     * @return
     */
    @RequestMapping("/userList")
    public String userList(){
        return "view/user_list";
    }
}
