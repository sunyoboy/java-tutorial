package com.designpattern.fascade;

/**
 * Created by root on 12/11/16.
 */
public class Computer {

    private CPU cpu;

    private Memrory mem;

    private HardDrive hardDrive;

    public Computer() {
        this.cpu = new CPU();
        this.mem = new Memrory();
        this.hardDrive = new HardDrive();
    }

    public Computer(CPU cpu, Memrory mem, HardDrive hardDrive) {
        this.cpu = cpu;
        this.mem = mem;
        this.hardDrive = hardDrive;
    }

    public void run() {
        hardDrive.readData();
        mem.load();
        cpu.processData();
    }

}

class CPU {
    public void processData() {

    }
}

class Memrory {
    public void load() {

    }
}

class HardDrive {
    public void readData() {

    }
}