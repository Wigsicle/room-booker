package application;

public class ModelTable {
	
	//Change columns
	String bookDate, bookingTime, bookerName, location;
	
	public ModelTable(String date, String bookingTime, String bookerName, String location) {
		this.bookDate = date;
		this.bookingTime = bookingTime;
		this.bookerName = bookerName;
		this.location = location;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getBookerName() {
		return bookerName;
	}

	public void setBookerName(String bookerName) {
		this.bookerName = bookerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
