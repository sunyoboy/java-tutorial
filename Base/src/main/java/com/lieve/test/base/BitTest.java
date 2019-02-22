package com.lieve.test.base;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/19/17
 * Time: 6:09 PM
 */
public class BitTest {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        ThreadPoolExecutor t;
        System.out.println(COUNT_BITS);
        System.out.println(Integer.toBinaryString(CAPACITY));
        System.out.println(CAPACITY);
        System.out.println(Integer.toBinaryString(SHUTDOWN));
        System.out.println(Integer.toBinaryString(TIDYING));
        System.out.println(Integer.toBinaryString(STOP));
        System.out.println(Integer.toBinaryString(TERMINATED));

        System.out.println(Integer.toBinaryString(256));
        System.out.println(Integer.toOctalString(56));
        System.out.println(Integer.toHexString(256));
    }
}
