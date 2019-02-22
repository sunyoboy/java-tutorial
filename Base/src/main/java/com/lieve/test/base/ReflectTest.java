package com.lieve.test.base;

import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/19/17
 * Time: 6:36 PM
 */
public class ReflectTest {
    public static void main(String[] args) {
        System.out.println(Byte.MIN_VALUE + "——" + Byte.MAX_VALUE); // -2^7 —— 2^7-1
        System.out.println(Short.MIN_VALUE + "——" + Short.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE + "——" + Integer.MAX_VALUE);
        System.out.println(Long.MIN_VALUE + "——" + Long.MAX_VALUE);
        System.out.println(Short.SIZE);
        System.out.println(Long.SIZE);
        System.out.println();;
        System.out.println(Void.TYPE.getName());
        java.lang.Object t;
        Collections collections;
    }
}

