package com.lieve.test.pattern.singleton;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/18/17
 * Time: 11:08 PM
 */
public class Singleton2 {
    public enum EnumSingleton {
        connnectionFactory;

        private Connection connection;

        private EnumSingleton () {
            // set connection

        }

        public Connection getConnection() {
            return connection;
        }
    }

    public static Connection getConnection() {
        return EnumSingleton.connnectionFactory.getConnection();
    }
}
