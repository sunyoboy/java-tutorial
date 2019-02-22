package com.lieve.test;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/24/17
 * Time: 10:30 PM
 */
public class IntAndTest {

    public static void test() {
        int a = 1 , b = 1 , c = 1 , d = 1;

        a++;
        ++b;

        c = c++;
        d = ++d;

        System.out.println(a + "\t" + b + "\t" + c + "\t" + d);
    }

    public static void main(String []args) {
        test();
        System.out.println(IntAndTest.class.getClassLoader());
    }
}