package com.lieve.test.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 12:22 PM
 */
public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy instance = new CGLibProxy();

    private CGLibProxy() {
    }

    public static CGLibProxy getInstance() {
        return instance;
    }

    /**
     * 获取代理方法
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> cls) {
        return (T)Enhancer.create(cls, this);
    }

    /**
     * 实现MethodInterceptor接口,并实现intercept方法.方法中最后一个
     * MethodProxy类型的参数需要注意,CGLib提供的是方法级别的代理,也可以理解为
     * 对方法的拦截.
     * 直接调用methodyProxy的invokeSuper方法,将被代理的对象obj及方法参数args传入
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o,objects);
        after();
        return result;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }

}
