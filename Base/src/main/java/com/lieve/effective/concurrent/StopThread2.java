package com.lieve.effective.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 4/4/17
 * Time: 8:22 PM
 */
public class StopThread2 {
    private static boolean stopRequest;

    private static synchronized void requestStop() {
        stopRequest = true;
    }

    private static synchronized boolean isStopRequest() {
        return stopRequest;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequest) {
                    i++;
                    System.out.println(i);
                }
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}
