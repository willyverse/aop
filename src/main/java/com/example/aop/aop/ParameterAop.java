package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // Aop로 동작 시킴
@Component // Spring에서 관리
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Before("cut()") // cut()이 실행되는 지점의 이전 시점
    public void before(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();
        for(Object obj: args) {
            System.out.println("type: " + obj.getClass().getSimpleName());
            System.out.println("value: " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("return obj: " + returnObj);
    }

}
