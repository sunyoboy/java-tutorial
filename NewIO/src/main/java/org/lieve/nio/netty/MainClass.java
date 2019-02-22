package org.lieve.nio.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/4/17
 * Time: 9:57 PM
 */
public class MainClass {
    public static void main(String[] args) {
        /*ChannelBuffer channelBuffer;
        ChannelBufferOutputStream channelBufferOutputStream;
         String urlStr=MainClass.class.getClassLoader().getResource("").getPath();
        File file=new File(urlStr);
        file.getParent();
        short a = 1;
        int b = 1;
        a += b;
        System.out.println(a);*/
        // a = a+ b;
        Task task = new Task();
        Thread t = new Thread(task);
        // t.run();
        t.start();
    }
}

class Task extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("run()");
        return;
    }
}
