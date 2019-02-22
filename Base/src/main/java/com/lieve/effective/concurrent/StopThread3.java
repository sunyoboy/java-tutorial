package com.lieve.effective.concurrent;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 4/4/17
 * Time: 8:27 PM
 */

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 4/4/17
 * Time: 8:17 PM
 */
public class StopThread3 {

    private static volatile boolean stopRequest;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                System.out.println(!stopRequest);
                while(!stopRequest) {
                    i++;
                    //System.out.println(i);
                }
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(stopRequest);
        stopRequest = true;
        System.out.println(stopRequest);
    }
}

