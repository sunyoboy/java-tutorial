package com.designpattern.interpreter;

import com.javafx.tools.doclets.formats.html.SourceToHTMLConverter;

/**
 * Created by root on 12/11/16.
 */
public class BExpression extends Expression {

    @Override
    public void interpret(Context context) {
        System.out.println("b express");
        String input = context.getInput();
        context.setInput(input.substring(1));
        context.setOutput(context.getOutput() + "2");
    }
}
