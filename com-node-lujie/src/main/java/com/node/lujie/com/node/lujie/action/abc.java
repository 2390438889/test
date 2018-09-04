package com.node.lujie.com.node.lujie.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class abc {

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello(){
        return "hello";
    }
}
