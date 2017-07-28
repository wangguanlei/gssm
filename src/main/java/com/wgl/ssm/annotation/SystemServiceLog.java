package com.wgl.ssm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @ClassName: SystemServiceLog 
* @Description: 系统级别的service层自定义注解
* <br>拦截service 
* @author wanggl 
* @date 2017年6月15日 下午3:56:50 
* @version V1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface SystemServiceLog {

	String description() default "";
	
}
