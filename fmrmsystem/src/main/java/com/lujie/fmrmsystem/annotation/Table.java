package com.lujie.fmrmsystem.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/**
 * 表映射
 */
public @interface Table  {
    String value() default "";
}
