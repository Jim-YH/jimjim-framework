package cn.jimjim.framework.datasource.annotation;

import java.lang.annotation.*;

/**
 * 切换数据注解 可以用于类或者方法级别 方法级别优先级 > 类级别
 * @author jim
 * @date 2019-07-31
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    //该值即key值
    String value() default "master";

}
