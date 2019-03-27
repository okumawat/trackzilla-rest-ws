package com.learn.trackzilla.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

	public static final String psqlUrl = "jdbc:postgresql://localhost:5432/javaws";
	public static final String mysqlUrl = "jdbc:mysql://localhost:3306/javaws";
	public static final String pusername="postgres";
	public static final String ppassword="postgres";
	public static final String musername="root";
	public static final String mpassword="mysql1";
	public static final String mysqlDriver="com.mysql.jdbc.Driver";
	public static final String psqlDriver = "org.postgresql.Driver";
	public static Connection getDBConnection() {
		Connection dbConnection = null;
		Properties props = new Properties();
		props.put("user", musername);
		props.setProperty("password",mpassword);
		//props.setProperty("ssl","true");
		try {
			Class.forName(mysqlDriver);
			dbConnection = DriverManager.getConnection(mysqlUrl, props);
			System.out.println("db connection:"+dbConnection.getSchema());
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		return dbConnection;
	}
}
