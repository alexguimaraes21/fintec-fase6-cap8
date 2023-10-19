package br.com.fiap.fase6cap8.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static String url = "jdbc:oracle:thin:@192.168.20.110:1521:XE";
	private static String username = "rm97596";
	private static String password = "fiap";
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		if(connection == null) {
			return DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
}
