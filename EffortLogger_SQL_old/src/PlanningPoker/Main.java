package PlanningPoker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner; // only used in main testing

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) throws SQLException {
		//5 employees will begin a planning session for project 1
		PlanningPokerFlow project1 = new PlanningPokerFlow();
		
		String url = "jdbc:mysql://localhost:3306/pokerplanning";
		String uname = "root";
		String password = "changeme";
		
		String projectQuery = "INSERT INTO project VALUES (SessionId, ProjectName)";
		String itemQuery = "INSERT INTO items Values (ItemId, SessionID, ItemName, ItemAvg)";
		String itemEvalQuery = "INSERT INTO itemeval(ItemEvalID, ItemName, CardValue, EmpID) VALUES";
		
		//Project table
		String sessionID = "1214";
		String projectName = "Project 1";
		
		//Item table
		String itemName = "Conops";
		int itemAvg = 0;
		
		
		//Item evaluation table
		int cardValue = 0;
		int empID = 0;
		
		Scanner scanner = new Scanner(System.in);	

        System.out.println("\nEnter the number of players:");
        int numPlayers = scanner.nextInt();
        boolean success = project1.setNumberOfPlayer(numPlayers);
        if (success) {
            System.out.println("Number of players set to " + numPlayers);
        } else {
            System.out.println("Invalid number of players. Please choose between " +
                    2 + " and " + 10);
        }
        String query = "";
        //Fill the item evaluation table
        
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, uname, password);
			Statement stm = con.createStatement();
			for(int i = 0; i < project1.getNumberOfPlayers(); i++) {
	        	empID = i*10+1;
	        	System.out.printf("Enter your card value for: player%d\n", i);
	            cardValue = scanner.nextInt();
	            int playersLeft = project1.giveCardValue(cardValue);
	            if (playersLeft >= 0) {
	                System.out.println("Card value accepted. " + playersLeft + " players left to give card values.");
	            } else if (playersLeft == -1) {
	                System.out.println("Invalid card value. Please choose one of the allowed values.");
	            } else {
	                System.out.println("All players have already played. Cannot accept more card values.");
	            }
	            query = "(" + i + "," + "'" + itemName + "'" + "," + cardValue + "," + empID + ")";
	            System.out.println(itemEvalQuery + query);
	    		itemAvg += cardValue;
	    		stm.addBatch(itemEvalQuery + query);
	        }
			stm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		itemAvg = itemAvg/project1.getNumberOfPlayers();
		try {
			Connection con = DriverManager.getConnection(url, uname, password);
			Statement stm = con.createStatement();
			itemQuery = itemQuery + "(" + "1" + "," + sessionID + ",'" + itemName + "'," + itemAvg + ")";
			stm.executeUpdate(itemQuery);
			projectQuery = projectQuery + "(" + sessionID + ",'" + projectName + "')";
			stm.executeUpdate(projectQuery);			
		} catch (SQLException e) {
			e.printStackTrace();
		}

        scanner.close();		
	}
}
