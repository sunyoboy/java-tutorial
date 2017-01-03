package com.designpattern.test;

/**
 * Created by root on 12/11/16.
 */
public class StringBufferTest {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer(10);
        sb.append("abcd");

        System.out.println(sb.length());
        System.out.println(sb.capacity());
    }
}
