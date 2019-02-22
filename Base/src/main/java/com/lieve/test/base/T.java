package com.lieve.test.base;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/20/17
 * Time: 9:24 AM
 */
public class T implements Cloneable {
    public static int k = 0;
    public static T t1 = new T("t1");
    public static T t2 = new T("t2");
    public static int n = 99;
    public static int i = print("i");

    static {
        print("静态块");
    }

    public int j = print("j");

    {
        print("构造块");
    }

    public T(String str) {
        System.out.println((++k) + ":" + str + "    i=" + i + "  n=" + n);
        ++n;
        ++i;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "   n=" + n);
        ++n;
        return ++i;
    }

    /*
    (1).首先T类被加载、连接后进行初始化，会先对字段k、t1、t2、i、n以及static块进行初始化。
    (2).t1实例的初始化会初始化实例成员j，(实际上先进行父类实例内容的初始化)先调用静态方法print，并执行实例初始化块{}，输出：
     1: j i=0 n= 0(i和n都还没有初始化)
     2:构造块 i=1 n=1
    (3)随后调用t1实例的构造函数，输出：
     3:t1 i=2 n=2
    (4).类似有t2实例的初始化：
     4: j i=3 n= 3
     5:构造块 i=4 n=4
     6:t2 i=5 n=5
    (5).i的初始化：
     7.i i=6 n=6
    (6).n的初始化和静态块的初始化：
     8.静态块 i=7 n=99(n已经被初始化)
    (7).t实例的初始化：
     9.j i=8 n= 100
     10.构造块 i=9 n= 101
     11.init i=10 n= 102
     */


    /**
     * k = 0
     * 1:t1    i=0  n=0
     * n=1, i=1
     *
     * @param args
     */
    public static void main(String[] args) {
        T t = new T("init");
    }
}