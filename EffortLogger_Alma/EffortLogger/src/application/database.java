package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class database {
	
	public static Connection connectDb(String db) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/" + db, "root", "changeme");
			
			return connect;
			
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}	
}
