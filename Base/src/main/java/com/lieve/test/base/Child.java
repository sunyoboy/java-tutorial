package com.lieve.test.base;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/20/17
 * Time: 9:08 AM
 */
public class Child extends Parent {

    public static int i = print("child static:i");

    static {
        print("子类静态初始化");
    }

    public int ii = print("child:ii");

    {
        print("子类实例初始化");
    }

    public Child(String str) {
        super(str);
        System.out.println("Child constructor: " + str);
    }

    public static int print(String str) {
        System.out.println("inital: " + str);
        return i;
    }
    /** 类的初始化会从祖先类到子类、按出现顺序，
     * 对类变量(静态变量)的初始化语句、静态初始化语句块依次进行初始化。
     * 而对类实例的初始化也类似，会从祖先类到子类、按出现顺序，
     * 对类成员的初始化语句、实例初始化块、构造方法依次进行初始化。*/
    /*
    initial: parent static:i
    initial: 父类静态初始化
    inital: child static:i
    inital: 子类静态初始化
    initial: parent:ii
    initial: 父类实例初始化
    parent constructor: cindy
    inital: child:ii
    inital: 子类实例初始化
    Child constructor: cindy
    */
    public static void main(String[] args) {
        Child child = new Child("cindy");
    }
}
