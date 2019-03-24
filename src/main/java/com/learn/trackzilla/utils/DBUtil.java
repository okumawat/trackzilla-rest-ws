package com.learn.trackzilla.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

	public static final String dbUrl = "jdbc:postgresql://localhost:5432/javaws";
	public static final String username="postgres";
	public static final String password="postgres";
	
	public static Connection getDBConnection() {
		Connection dbConnection = null;
		Properties props = new Properties();
		props.put("user", username);
		props.setProperty("password",password);
		//props.setProperty("ssl","true");
		try {
			Class.forName("org.postgresql.Driver");
			dbConnection = DriverManager.getConnection(dbUrl, props);
			System.out.println("db connection:"+dbConnection.getSchema());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbConnection;
	}
}
