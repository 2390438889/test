package com.lujie.fmrmsystem.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/**
 * 表字段映射
 */
public @interface Column {
    //字段名
    String name();
    //字段类型
    String type();
    //其他属性
    String other() default "";
    //描述
    String comment() default "";
}
