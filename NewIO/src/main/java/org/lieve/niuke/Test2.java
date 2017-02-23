package org.lieve.niuke;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/18/17
 * Time: 4:55 PM
 */
public class Test2
{
    public void add(Byte b)
    {
        b = b++;
    }
    public void test()
    {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.print(a + " ");
        add(b);
        System.out.print(b + "");
    }

    public static void main(String[] args) {
        new Test2().test();
    }
}