package com.designpattern.mediator;

/**
 * Created by root on 12/11/16.
 */
public class ConcreteMediator implements IMediator {

    ColleagueA talk;

    ColleagueB fight;

    public void fight() {
        System.out.println("fight");
    }

    public void talk() {
        System.out.println("talk");
    }

    public void registerA(ColleagueA a) {
        this.talk = a;
    }

    public void registerB(ColleagueB b) {
        this.fight = b;
    }
}
