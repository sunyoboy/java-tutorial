package com.designpattern.fascade;

import org.junit.Test;

/**
 * Created by root on 12/11/16.
 */
public class ComputerTest {

    @Test
    public void testRun1() throws Exception {
        Computer computer = new Computer();
        computer.run();
    }
}