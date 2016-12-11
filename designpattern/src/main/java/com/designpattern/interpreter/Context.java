package com.designpattern.interpreter;

/**
 * Created by root on 12/11/16.
 */
public class Context {

    private String input;

    private String output;

    public Context(String input) {
        this.input = input;
        this.output = "";
    }


    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}

