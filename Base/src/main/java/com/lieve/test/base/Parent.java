package com.lieve.test.base;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/20/17
 * Time: 9:05 AM
 */
public class Parent {

    public static int i = print("parent static:i");

    public int ii = print("parent:ii");

    static {
        print("父类静态初始化");
    }

    {
        print("父类实例初始化");
    }

    public Parent(String str) {
        System.out.println("parent constructor: " + str);
    }

    public static int print(String str) {
        System.out.println("initial: " + str);
        return i;
    }
}
