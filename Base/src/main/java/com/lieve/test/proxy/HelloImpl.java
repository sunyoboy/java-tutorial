package com.lieve.test.proxy;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 11:25 AM
 */
public class HelloImpl implements Hello {

    @Override
    public void say(String name) {
        System.out.println("Hello " + name);
    }
}
