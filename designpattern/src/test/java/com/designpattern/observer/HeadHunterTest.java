package com.designpattern.observer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by root on 12/11/16.
 */
public class HeadHunterTest {

    @Test
    public void testNotifyAllObservers() throws Exception {
        HeadHunter headHunter = new HeadHunter();

        headHunter.registerObserver(new JobSeeker("Mike"));
        headHunter.registerObserver(new JobSeeker("John"));
        headHunter.registerObserver(new JobSeeker("Tom"));


        headHunter.addJob("IT Coder");
        headHunter.addJob("Teacher");
    }
}