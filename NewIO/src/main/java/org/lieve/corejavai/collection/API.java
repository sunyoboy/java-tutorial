package org.lieve.corejavai.collection;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/25/17
 * Time: 9:23 AM
 */
public class API {


    Collection collection = new ArrayList<Integer>();
    // Collections 类的算法
    // Collections.min(collection);

/*    List<JMenuItem> getAllItems(final JMenu menu) {
        return new AbstractList<>() {
            public JMenuItem get(int i) {
                return menu.getItem(i);
            }

            public int size() {
                return menu.getItemCount();
            }
        };
    }*/

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.newCondition();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        t.isInterrupted();

        Thread.interrupted();
    }
}
