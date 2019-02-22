package com.lieve.test.pattern.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/18/17
 * Time: 11:23 PM
 */
public class Singleton4 {
    private static Singleton4 instance = null;

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        if(instance == null) {
            synchronized (Singleton4.class) {
                if(instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
