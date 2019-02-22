package com.lieve.test.proxy.aop;

import com.lieve.test.proxy.Apology;
import com.lieve.test.proxy.GreetingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 引入增强客户端
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 1:59 PM
 */
public class Client4 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 转型为目标类,而非Greeting接口
        GreetingImpl greetingImpl = (GreetingImpl)applicationContext.getBean("greetingProxy");
        greetingImpl.sayHello("Jack");

        // 将目标类强制向上转型为Apology接口(这是引入增强带给我们的特性,即"接口动态实现"功能)
        Apology apology = (Apology)greetingImpl;

        apology.saySorry("Sun");
    }
}
