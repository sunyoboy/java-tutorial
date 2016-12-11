package com.designpattern.mediator;

/**
 * Created by root on 12/11/16.
 */
public class ColleagueB extends Colleague {

    public ColleagueB(IMediator mediator) {
        this.mediator = mediator;
        this.mediator.registerB(this);
    }

    @Override
    public void doSomething() {
        this.mediator.fight();
    }
}
