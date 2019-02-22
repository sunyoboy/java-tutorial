package com.lieve.test.base;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/20/17
 * Time: 10:55 AM
 */
public class ExceptionTest {
    public int inc(){
        int x;
        try{
            x=1;
            return x;
        }catch(Exception e){
            x=2;
            return x;
        }finally{
            x=3;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ExceptionTest().inc());
        java.lang.Object object;
    }
}
