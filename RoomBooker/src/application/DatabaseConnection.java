package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	Connection connection = null;
	
	public Connection getConnection() {
		String databaseName = "roomsdb";
		String url = "jdbc:mysql://localhost:3306/" + databaseName;
		String username = "root";
		String password = "R%85wEuX!gVqWThL";
		
		System.out.println("Connecting database...");
		
		try {
			//Connect to database
			connection = DriverManager.getConnection(url, username, password); //Connecting to database
		    System.out.println("Database connected!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
}
