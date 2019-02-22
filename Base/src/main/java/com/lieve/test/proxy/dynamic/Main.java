package com.lieve.test.proxy.dynamic;

import com.lieve.test.proxy.Hello;
import com.lieve.test.proxy.HelloImpl;
import com.lieve.test.proxy.HelloProxy;

import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 11:37 AM
 */
public class Main {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        /**
         * DynamicProxy类包装HelloImpl实例,然后再调用JDK给我们提供的Proxy类的工厂方法
         * Proxy.newProxyInstance去动态的创建一个Hello接口的代理类
         */
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
        Hello helloProxy = (Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                dynamicProxy
        );

        helloProxy.say("sunlj");

        DynamicProxy dynamicProxy1 = new DynamicProxy(new HelloImpl());
        Hello hello1 = dynamicProxy1.getProxy();
        hello1.say("Jack");


    }


}
