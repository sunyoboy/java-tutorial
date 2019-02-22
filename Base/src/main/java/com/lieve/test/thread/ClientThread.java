package com.lieve.test.thread;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 2:41 PM
 */
public class ClientThread extends Thread {
    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++) {
            System.out.println(Thread.currentThread().getName() +
            " => " + sequence.getNumber());
        }
    }
}
