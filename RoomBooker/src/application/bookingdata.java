package application;

public class bookingdata {
	
	static private String username;
	
	static private String booktype;
    
	static private String booktime;
    
	static private String booklocation;
    
	static private String bookdate;
	
	static private String table;

	static public String getBooktype() {
		return booktype;
	}

	public static void setBooktype(String booktype) {
		bookingdata.booktype = booktype;
	}

	static public String getBooktime() {
		return booktime;
	}

	public static void setBooktime(String booktime) {
		bookingdata.booktime = booktime;
	}

	static public String getBooklocation() {
		return booklocation;
	}

	public static void setBooklocation(String booklocation) {
		bookingdata.booklocation = booklocation;
	}

	static public String getBookdate() {
		return bookdate;
	}

	public static void setBookdate(String string) {
		bookingdata.bookdate = string;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		bookingdata.username = username;
	}

	public static String getTable() {
		return table;
	}

	public static void setTable(String table) {
		bookingdata.table = table;
	}

	

}
