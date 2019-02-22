package com.lieve.test.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 11:28 AM
 */
public class Main {
    public static void main(String[] args) {
        Hello hello = new HelloProxy();
        hello.say("sunlj");
    }
}
