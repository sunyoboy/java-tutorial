package org.lieve.concurrent;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/4/17
 * Time: 12:04 AM
 */
public class Main {
    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Callable callable;
        Executors executors;
        ConcurrentSkipListMap t;
        ConcurrentSkipListSet s;
        CopyOnWriteArrayList list;
        CopyOnWriteArraySet set;
        int cpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNum);
    }
}

/*
interface Callable<V> {
    V call() throws Exception;
}
*/
