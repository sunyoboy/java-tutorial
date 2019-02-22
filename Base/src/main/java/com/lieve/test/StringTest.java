package com.lieve.test;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/24/17
 * Time: 9:56 PM
 */
public class StringTest {
    public static void main(String[] args) {
        testForJDK17();
    }

    public static void test1() {
        String a = "a" + "b" + 1;
        String b = "ab1";
        println(a == b);
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }

    private final static String getA() {return "a";}
    public static void test2() {
        String a = "a";
        final String c = "a";

        String b = a + "b";
        String d = c + "b";
        String e = getA() + "b";

        String compare = "ab";
        println(b == compare);
        println(d == compare);
        println(e == compare);
    }

    public static void test3() {
        String a = "a";
        String b = a + "b";
        String c = "ab";
        String d = new String(b);
        println(b == c); // false
        println(c == d); // false
        println(c == d.intern()); // true
        println(b.intern() == d.intern()); // true
    }

    /**
     * 该代码仅仅用于测试JDK1.7
     * 这里单独用e、f来做的原因是不想和前面的程序已经生成的常量池相互影响
     */
    public static void testForJDK17() {
        String a = "e";
        String b = "f";
        String c = a + b;
        String d = a + b;
        System.out.println(c == c.intern());
        System.out.println(d == d.intern());
        System.out.println(c == d.intern());
        System.out.println(System.identityHashCode(c));
        System.out.println(System.identityHashCode(d));
        System.out.println(System.identityHashCode(c.intern()));
        System.out.println(System.identityHashCode(d.intern()));
        System.out.println(System.identityHashCode("ef"));

        StringBuilder stringBuilder = new StringBuilder(10);
    }
}