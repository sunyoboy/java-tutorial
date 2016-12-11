package com.designpattern.interpreter;

/**
 * Created by root on 12/11/16.
 */
public class AExpression extends Expression {
    @Override
    public void interpret(Context context) {
        System.out.println("a express");
        String input = context.getInput();
        context.setInput(input.substring(1));
        context.setOutput(context.getOutput() + "1");
    }

}
