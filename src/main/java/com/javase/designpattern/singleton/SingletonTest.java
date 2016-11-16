package com.designpattern.singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingletonTest {
	public static void main(String[] args) {
		
		System.out.println("Singleton.EnumSingleton.connectionFactory -> " + 
				Singleton.EnumSingleton.connectionFactory);
		
		System.out.println(" " + Size.small);
		System.out.println("ordinal " + Size.small.ordinal());
		System.out.println(Enum.valueOf(Size.class, "small"));
		System.out.println(Size.small.toString());
		Size[] values = Size.values();
		
		Connection conn = Singleton.getConnection();
		String sql = "select * from goods";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				// System.out.print(rs.getString(1) + "\t");
				// System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public enum Size {
		small;
/*		private String abbreviation; // abbreviation

		private Size(String abbreviation) {
			this.abbreviation = abbreviation;
		}

		public String getAbbreviation() {
			return abbreviation;
		}*/

/*		public void setAbbreviation(String abbreviation) {
			this.abbreviation = abbreviation;
		}*/
		
		
	}
}
