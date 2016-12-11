package com.designpattern.observer;

/**
 * Created by root on 12/11/16.
 */
public class JobSeeker implements Observer {

    private String name;

    public JobSeeker(String name) {
        this.name = name;
    }

    public void update(Subject subject) {
        System.out.println(this.name + " get notified!");
        System.out.println(subject);
    }
}
