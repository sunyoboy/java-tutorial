package org.lieve.concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/4/17
 * Time: 12:01 AM
 */
public class SimpleThreadFactory implements ThreadFactory {


    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
