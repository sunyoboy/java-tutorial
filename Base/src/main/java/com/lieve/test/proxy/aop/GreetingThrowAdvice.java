package com.lieve.test.proxy.aop;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 1:36 PM
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.println("Throw Exception");
        System.out.println("Target class: " + target.getClass().getName());
        System.out.println("Method Name : " + method.getName());
        System.out.println("Exception Message : " + e.getMessage());
    }

}
