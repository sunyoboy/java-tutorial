package com.lieve.test.pattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/18/17
 * Time: 11:15 PM
 */
public class Singleton3 implements Serializable {

    // 静态内部类的方式
    private static class InnerClass {
        private static Singleton3 instance = new Singleton3();
    }

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        return InnerClass.instance;
    }

    // 如果遇到序列化对象时,使用默认的方式运行是非线程安全的.解决方法:在反序列化中使用readResolve()方法
    protected Object readResolve() throws ObjectStreamException {
        return InnerClass.instance;
    }
}
