package com.lieve.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/18/17
 * Time: 11:43 PM
 */
public class Test {
    public static void main(String[] args) {
        java.lang.Object object;

        HashMap hashMap;

        String className = "java.util.Date";
        try {
           // Date date = (Date)Class.forName(className).newInstance();
            Object date = Class.forName(className).newInstance();
            System.out.println(date);
            date.getClass().getFields();
            date.getClass().getConstructors();
            date.getClass().getMethods();

            date.getClass().getDeclaredConstructors();
            date.getClass().getDeclaredMethods();
            date.getClass().getDeclaredFields();

            Field field;
            Method method;
            Constructor constructor;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AtomicInteger integer;
        System.out.println(Integer.SIZE);
    }
/*
    public final int addAndGet(int delta) {
        for(;;) {
            int current = get();
            int next = current + delta;
            if(compareAndSet(current, next))  {
                return next;
            }
        }
    }*/


}
