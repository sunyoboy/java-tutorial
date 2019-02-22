package com.lieve.base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 3:39 PM
 */
public class DBUtil {

    // config
    private static final String driver = "com.msyql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/base";
    private static final String username = "mysql";
    private static final String password = "mysql";

    // 定义一个用于存放数据库连接的线程局部变量
    private static ThreadLocal<Connection> connContainer =
            new ThreadLocal<Connection>();

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        Connection conn = connContainer.get();
        try {
            if(conn == null) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connContainer.set(conn);
        }
        return conn;
    }

    public static void close() {
        Connection conn = connContainer.get();
        try {
            if(conn == null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connContainer.remove();
        }
    }


}
