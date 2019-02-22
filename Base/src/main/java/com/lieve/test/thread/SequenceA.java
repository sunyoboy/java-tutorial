package com.lieve.test.thread;

import com.lieve.test.proxy.aop.Client;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 2:44 PM
 */
public class SequenceA implements Sequence {

    private static int number;

    @Override
    public int getNumber() {
        number += 1;
        return number;
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceA();
        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
