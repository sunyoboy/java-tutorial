package com.lieve.test.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/18/17
 * Time: 9:37 PM
 */
public class ConnectionTest {
    public static void main(String[] args) throws SQLException {
        System.out.println(exceptionTest());;
    }

    public static int exceptionTest() {
        int ret = 0;
        try{
            throw new Exception();
        }
        catch(Exception e){
            ret = 1;
            return ret;
        }
        finally{
            ret = 2;
        }
    }
}
