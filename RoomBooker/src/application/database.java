package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class database {
	DatabaseConnection connectNow = new DatabaseConnection();
	Connection connectDB = connectNow.getConnection();
	
	ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
	
	protected ObservableList<ModelTable> db(String table) {
		//Tableview sql dump
				try {
					String tablesql = "SELECT * FROM "+ table +"";
					ResultSet  rs = connectDB.createStatement().executeQuery(tablesql);
			
					//Adding data to the tableview. Change the column indexes.
					while (rs.next()) {
						oblist.add(new ModelTable(rs.getString("bookDate"), rs.getString("bookingTime"), rs.getString("bookerName"), rs.getString("location")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return oblist;
	}
	protected boolean checkDuplicateEntry() {
		 boolean error = false;
		 try {
			final String duplicatesql = "SELECT * FROM "+ bookingdata.getTable() +" WHERE (location = '"+ bookingdata.getBooklocation() +"' AND bookDate = '"+ bookingdata.getBookdate() +"' AND bookingTime = '"+ bookingdata.getBooktime() +"')";
			final PreparedStatement ps  = connectDB.prepareStatement(duplicatesql);
			final ResultSet resultSet = ps.executeQuery();
			if (resultSet.next() == true) {
				error = true;
			} else {
				error = false;
				insertData();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return error;
	 }
	
	private void insertData() {
		
		try {
			final String insertsql = "INSERT INTO "+ bookingdata.getTable() +" (location, bookDate, bookingTime, bookerName) " + "VALUES ('"+ bookingdata.getBooklocation() +"','"+ bookingdata.getBookdate() +"', '"+ bookingdata.getBooktime() +"', '"+ bookingdata.getUsername() +"')";
			final PreparedStatement ps = connectDB.prepareStatement(insertsql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		update();
	}
	
	protected void update() {
		try {
			final String updatesql = "SELECT * FROM "+ bookingdata.getTable() +"";
			final PreparedStatement ps = connectDB.prepareStatement(updatesql);
			final ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				oblist.add(new ModelTable(resultSet.getString("bookDate"), resultSet.getString("bookingTime"), resultSet.getString("bookerName"), resultSet.getString("location")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
}
