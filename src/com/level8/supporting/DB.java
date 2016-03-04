package com.level8.supporting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Database class. Sets up and simplifies database interactions
 * @author Ben McLean
 * @version 1.0
 */
public class DB {
	private static final String HOST = "";
	private static final String DATABASE = "";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	@SuppressWarnings("unused")
	private static final String PORT = "3306";
	
	MysqlDataSource dataSource;
	HttpServletResponse response;
	
	public Connection conn;
	public static DB db = new DB();
	
	private DB(){
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(USERNAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setServerName(HOST);
		dataSource.setDatabaseName(DATABASE);
		
		this.dataSource = dataSource;
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get database name
	 * @return Databse name
	 */
	public static String dbN(){
		return DATABASE;
	}
	
	/**
	 * Gets the database connection
	 * @return Database connection
	 */
	public Connection getDatabase(){
		return conn;
	}
	
	/**
	 * Executes a query
	 * @param query The query string
	 * @param check Check the query string
	 * @return The results from the query
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query, boolean check) throws SQLException{
		Connection conn = (Connection) dataSource.getConnection();
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
}

