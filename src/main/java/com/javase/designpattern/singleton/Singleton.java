package com.javase.designpattern.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
	public enum EnumSingleton {
		connectionFactory;
		private Connection connection;
		private EnumSingleton() {
			try {
				String url="jdbc:mysql://localhost:3306/flower";
				String username="flower";
				String password="flower";
				String driverName="com.javase.mysql.jdbc.Driver";
				Class.forName(driverName);
				connection = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		public Connection getConnection() {
			return connection;
		}
	}
	
	public static Connection getConnection() {
		return EnumSingleton.connectionFactory.getConnection();
	}
}
