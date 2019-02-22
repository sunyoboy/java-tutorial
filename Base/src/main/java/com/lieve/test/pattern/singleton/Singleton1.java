package com.lieve.test.pattern.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/18/17
 * Time: 11:02 PM
 */

/**
 * 静态代码块中的代码在使用类的适合已经执行了.
 */
public class Singleton1 {

    private static Singleton1 instance = null;

    private Singleton1() {

    }

    static {
        instance = new Singleton1();
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}
