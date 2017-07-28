package com.wgl.ssm.spring.aop;

import java.lang.reflect.Method;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.wgl.ssm.annotation.Log;

@Aspect
@Component
public class LogAop_1{

	ThreadLocal<Long> time=new ThreadLocal<Long>();
    ThreadLocal<String> tag=new ThreadLocal<String>();
     
    /**
     * 在所有标注@Log的地方切入
     * @param joinPoint
     */
    @Before("@annotation(com.wgl.ssm.annotation.Log)")
    public void beforeExec(JoinPoint joinPoint){
        System.out.println("快闪开,我要开始切入了");
        time.set(System.currentTimeMillis());
        tag.set(UUID.randomUUID().toString());
         
        info(joinPoint);
         
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method method=ms.getMethod();
        System.out.println(method.getAnnotation(Log.class).name()+"标记"+tag.get());
    }
     
    @After("@annotation(com.wgl.ssm.annotation.Log)")
    public void afterExec(JoinPoint joinPoint){
        System.out.println("回来吧,切入结束了");
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method method=ms.getMethod();
        System.out.println("标记为"+tag.get()+"的方法"+method.getName()+"运行消耗"+(System.currentTimeMillis()-time.get())+"ms");
    }
     
    @Around("@annotation(com.wgl.ssm.annotation.Log)")
    public void aroundExec(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("我是Around，来打酱油的");
        pjp.proceed();
    }
     
    private void info(JoinPoint joinPoint){
        System.out.println("--------------------------------------------------");
        System.out.println("King:\t"+joinPoint.getKind());
        System.out.println("Target:\t"+joinPoint.getTarget().toString());
        Object[] os=joinPoint.getArgs();
        System.out.println("Args:");
        for(int i=0;i<os.length;i++){
            System.out.println("\t==>参数["+i+"]:\t"+os[i].toString());
        }
        System.out.println("Signature:\t"+joinPoint.getSignature());
        System.out.println("SourceLocation:\t"+joinPoint.getSourceLocation());
        System.out.println("StaticPart:\t"+joinPoint.getStaticPart());
        System.out.println("--------------------------------------------------");
    }
	
}
