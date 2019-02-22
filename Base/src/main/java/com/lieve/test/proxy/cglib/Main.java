package com.lieve.test.proxy.cglib;

import com.lieve.test.proxy.Hello;
import com.lieve.test.proxy.HelloImpl;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 12:38 PM
 */
public class Main {
    public static void main(String[] args) {
        // 初始代码
        /*CGLibProxy cgLibProxy = new CGLibProxy();
        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say("Tom");*/

        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("You");
    }
}
