package com.designpattern.interpreter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by root on 12/11/16.
 */
public class BExpressionTest {

    @Test
    public void testInterpret() throws Exception {
        String abc = "abba";
        Context context = new Context(abc);
        List<Expression> list = new ArrayList<Expression>();
        list.add(new AExpression());
        list.add(new BExpression());

        for(Expression expression : list) {
            expression.interpret(context);
        }

        System.out.println(context.getOutput());
    }
}