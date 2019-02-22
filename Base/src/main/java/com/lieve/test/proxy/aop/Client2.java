package com.lieve.test.proxy.aop;

import com.lieve.test.proxy.Greeting;
import com.lieve.test.proxy.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 1:25 PM
 */
public class Client2 {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(); // 创建代理工厂
        proxyFactory.setTarget(new GreetingImpl()); // 射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());

        Greeting greeting = (Greeting) proxyFactory.getProxy(); // 从代理工厂中获取代理

        greeting.sayHello("Jack");
    }
}
