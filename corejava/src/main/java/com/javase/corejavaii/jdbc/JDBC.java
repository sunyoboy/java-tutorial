package com.javase.corejavaii.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * Created by root on 12/27/16.
 */
public class JDBC {

    public static void main(String[] args) {
        try {
            query();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("src/main/java/jdbc.properties"))) {
            props.load(in);
        }
        String driver = props.getProperty("jdbc.driver");
        Class.forName(driver);
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.user");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, user, password);
    }

    public static void query() throws SQLException, IOException, ClassNotFoundException {
        try(Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE Greetings IF EXISTS");
            stmt.executeUpdate("CREATE TABLE Greetings(Message VARCHAR(20))");
            stmt.executeUpdate("INSERT INTO Greetings VALUES('Hello World!')");
            try(ResultSet rs = stmt.executeQuery("SELECT Message FROM Greetings")) {
                while(rs.next()) {
                    System.out.println(rs.getString("Message"));
                }
            }
            stmt.executeUpdate("DROP TABLE Greetings");
        }
    }
}
