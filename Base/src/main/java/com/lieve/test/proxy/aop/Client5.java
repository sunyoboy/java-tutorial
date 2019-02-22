package com.lieve.test.proxy.aop;

import com.lieve.test.proxy.Greeting;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 声明式
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 2:14 PM
 */
public class Client5 {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("aop-advice.xml");

        Greeting greeting = (Greeting) applicationContext.getBean("greetingProxy");
        greeting.sayHello("Jack");
    }
}
