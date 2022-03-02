package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;

public class login {
	DatabaseConnection connectNow = new DatabaseConnection();
	Connection connectDB = connectNow.getConnection();
	
	protected boolean validateLogin(ActionEvent event, String userName, String pwd) {
		boolean login = false;
		
		//DatabaseConnection connectNow = new DatabaseConnection();
		//Connection connectDB = connectNow.getConnection();
		
		try {
			final String verifyloginsql = "SELECT count(1) FROM users WHERE Username = '" + userName + "' AND Password = '" + pwd + "'";
			final PreparedStatement ps = connectDB.prepareStatement(verifyloginsql);
			final ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				if(resultSet.getInt(1) == 1) {	
					login = true;
					bookingdata.setUsername(userName);
				} else {
					login = false;
				} 
			}
		}catch (Exception e) {
					e.printStackTrace();
		}
		return login;
	}
}
