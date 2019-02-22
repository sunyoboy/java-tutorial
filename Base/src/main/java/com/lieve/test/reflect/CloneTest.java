package com.lieve.test.reflect;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/19/17
 * Time: 1:40 PM
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p = new Person("name", 3);
        Object p1 = p.clone();
        System.out.println(p == p1);
        System.out.println(p.getClass() == p1.getClass());
        System.out.println(p.equals(p1));
        System.out.println(p + " " + p1);
    }
}
