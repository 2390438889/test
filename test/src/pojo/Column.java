package pojo;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    //字段名
    String name();
    //字段类型
    String type();
    //其他限制
    String where() default "";
    //描述
    String comment() default "";
}
