package com.lujie.demo.action;

import com.lujie.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 成绩信息action
 * @author 卢杰
 * 时间:2018.8.3
 */
@RestController
@RequestMapping("/score")
public class ScoreAction {
    @Autowired
    private ScoreService scoreService;
    /**
     * 获得成绩平均分和及格率
     */
    @RequestMapping("/passRate")
    public List passRateScore(){
        List list=null;
        list=scoreService.queryByPassRateScore();

        return list;
    }
}
