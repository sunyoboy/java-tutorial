package com.lieve.test.pattern.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/20/17
 * Time: 10:31 AM
 */
public class Singleton5 {
    private static Singleton5 instance = null;

    private Singleton5() {

    }

    private synchronized static Singleton5 getInstance() {
        if(instance == null) {
            instance = new Singleton5();
        }
        return instance;
    }
}
