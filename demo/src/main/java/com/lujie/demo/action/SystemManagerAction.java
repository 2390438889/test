package com.lujie.demo.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制页面跳转的控制器
 * author 卢杰
 * 时间:2018.8.3
 */
@Controller
public class SystemManagerAction {
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    /**
     * 跳转到学生页面
     * @return
     */
    @RequestMapping("/studentList")
    public String studentList(){
        return "view/studentList";
    }

    /**
     * 跳转到学生信息编辑界面
     * @param s_id
     * @param model
     * @return
     */
    @RequestMapping("/studentEdit")
    public String studentEdit(Integer s_id, Model model){
        //通过编号获得学生信息
        model.addAttribute("s_id",s_id);
        return "view/studentEdit";
    }

}
