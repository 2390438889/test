package com.lujie.fmrmsystem;

import com.lujie.fmrmsystem.Service.ExcelFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimerTest {
    @Autowired
    private ExcelFileService excelFileService;
    @Test
    public void test(){
        excelFileService.uploadExcel();
    }
}
