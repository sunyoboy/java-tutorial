package com.lieve.base.util.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 10:16 AM
 */
public final class DatabaseHelper {

    private static final ThreadLocal<Connection> CONNECTION_HOLDER =
            new ThreadLocal<Connection>();


    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if(conn == null) {
/*            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                CONNECTION_HOLDER.set(conn);
            }*/
            // CONNECTION_HOLDER.get();
            // CONNECTION_HOLDER.set();
            // CONNECTION_HOLDER.remove();
        }
        return conn;
    }
}
