package com.lieve.test.collection;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/20/17
 * Time: 1:49 PM
 */
public class CollectionTest {
    public static void main(String[] args) {
        HashMap hashMap;

        // hashMap.put()
        Hashtable hashtable;
        BigInteger bigInteger = BigInteger.valueOf(100);
        System.out.println(bigInteger);
        int b = 100;
        BigInteger c = bigInteger.add(BigInteger.valueOf(b));
        System.out.println(c);

        BigInteger d = c.multiply(bigInteger.add(BigInteger.valueOf(2)));
        System.out.println(d);

        BigDecimal bigDecimal = BigDecimal.valueOf(1.0D);
        System.out.println(bigDecimal.add(BigDecimal.TEN));
        ConcurrentHashMap hashMap1;

        System.out.println(CollectionTest.class.getClassLoader());
        System.out.println(CollectionTest.class.getClassLoader().getParent());
        System.out.println(CollectionTest.class.getClassLoader().getParent().getParent());
        sun.misc.Launcher t;

        ArrayList arrayList;
        LinkedList linkedList;
        Vector v;

        HashMap map;

        ConcurrentHashMap concurrentHashMap;
    }
}
