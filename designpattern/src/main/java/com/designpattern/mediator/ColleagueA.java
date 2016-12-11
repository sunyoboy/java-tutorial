package com.designpattern.mediator;

/**
 * Created by root on 12/11/16.
 */
public class ColleagueA extends Colleague {

    public ColleagueA(IMediator mediator) {
        this.mediator = mediator;
        this.mediator.registerA(this);
    }


    @Override
    public void doSomething() {
        this.mediator.talk();
    }
}
