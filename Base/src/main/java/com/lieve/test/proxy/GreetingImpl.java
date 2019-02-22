package com.lieve.test.proxy;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 1:19 PM
 */
@Component
public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
        throw new RuntimeException("Error");
    }

    public void goodMorning(String name) {
        System.out.println("Good Morning " + name);
    }

    public void goodNight(String name) {
        System.out.println("Good Night! " + name);
    }
}
