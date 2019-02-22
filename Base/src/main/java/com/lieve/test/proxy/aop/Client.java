package com.lieve.test.proxy.aop;

import com.lieve.test.proxy.Greeting;
import com.lieve.test.proxy.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 1:18 PM
 */
public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(); // 创建代理工厂
        proxyFactory.setTarget(new GreetingImpl()); // 射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAdvice()); // 添加前置增强
        proxyFactory.addAdvice(new GreetingAfterAdvice()); // 添加后置增强

        Greeting greeting = (Greeting) proxyFactory.getProxy(); // 从代理工厂中获取代理

        greeting.sayHello("Jack");
    }
}
