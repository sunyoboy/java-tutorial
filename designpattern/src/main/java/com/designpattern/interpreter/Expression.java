package com.designpattern.interpreter;

/**
 * Created by root on 12/11/16.
 */
abstract class Expression {
    public abstract void interpret(Context context);
}