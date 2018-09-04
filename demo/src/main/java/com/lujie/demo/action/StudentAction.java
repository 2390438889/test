package com.lujie.demo.action;

import com.lujie.demo.bean.PageBean;
import com.lujie.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
/**
 * 学生信息控制器
 * @author 卢杰
 * 时间：2018.8.3
 */
@RequestMapping("/student")
public class StudentAction {
    @Autowired
    private StudentService studentService;

    /**
     * 获得所有的学生信息
     * @param pageBean  //分页信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageBean stuList(PageBean pageBean,@RequestParam Map keyword){
        //去除page 和 limit 属性
        keyword.remove("page");
        keyword.remove("limit");
        List list=studentService.queryAllStudent(pageBean,keyword);
        pageBean.setData(list);
        pageBean.setStatus("ok");
        pageBean.setCode(0);
        pageBean.setCount(studentService.queryStudentCount());
        pageBean.setMsg("成功！");
        return pageBean;
    }

    @RequestMapping("/del")
    @ResponseBody
    /**
     * 通过编号删除学生信息
     * @Param s_id  需要删除学生的编号
     */
    public int stuDel(@RequestParam("s_id") Integer s_id){
        int flag=1;
        if(s_id==null){
            flag=0;
        }
        studentService.deleteByIdStudent(s_id);
        return flag;
    }

    /**
     * 保存学生信息
     * @param maps   学生信息
     * @return 即将跳转到的学生列表界面
     */
    @RequestMapping("/save")
    @ResponseBody
    public int stuSave(@RequestParam Map maps){
        StringBuffer sLike=new StringBuffer();
        List list=new ArrayList();
       for(Object o:maps.entrySet()){
           Map.Entry entry= (Map.Entry) o;
           if(entry.getKey().toString().startsWith("sLike")){
               sLike.append(entry.getValue()+",");
               list.add(entry.getKey());
           }
       }

       for(Object o:list){
           maps.remove(o);
       }

       //去除最后的逗号
        if(sLike.length()>0){
            sLike.delete(sLike.length()-1,sLike.length());
        }else{
           sLike.append("");
        }


       maps.put("s_like",sLike);
       //添加数据
        if(maps.get("s_id").toString().trim().equals("")){
            studentService.insertStudent(maps);
        }else{
            studentService.updateStudent(maps);
        }

       return 0;
    }

    /**
     * 通过学生编号获得数据
     * @param s_id  需要获取数据的学生编号
     * @return
     */
    @RequestMapping("/find")
    @ResponseBody
    public Map queryByIdStudent(@RequestParam Integer s_id){
        Map map=null;
        map=studentService.queryByIdStudent(s_id);
        return map;
    }

}
