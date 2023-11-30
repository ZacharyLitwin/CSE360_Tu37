//Author: Alma Babbit
// Group: TU-37
//Purpose: Defines the connection to the database
package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
	
	public static Connection connectDb(String db) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/" + db, "root", "password");
			
			return connect;
			
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}	
}
