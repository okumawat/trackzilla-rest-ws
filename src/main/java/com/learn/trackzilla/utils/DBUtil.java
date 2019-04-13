package com.learn.trackzilla.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {

	private static DataSource ds;
	static {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/javaws");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			dbConnection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("db connection successful...");
		
		return dbConnection;
	}
}
