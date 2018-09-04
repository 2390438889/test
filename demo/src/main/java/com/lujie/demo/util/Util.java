package com.lujie.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用工具方法
 */
public class Util {

    /**
     * 格式化日期
     * @param date 需要格式化的日期对象
     * @return
     */
    public static String dateFormat(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}
