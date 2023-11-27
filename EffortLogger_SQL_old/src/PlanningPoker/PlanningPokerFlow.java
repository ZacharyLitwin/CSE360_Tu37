package PlanningPoker;

//Author: Zachary Litwin
import java.util.ArrayList;
import java.util.Scanner; // only used in main testing

public class PlanningPokerFlow {
	
	// sets default min and max number of players
	private static final int MAX_PLAYERS = 10;
	private static final int MIN_PLAYERS = 2;
	
	// sets the allowed card values
	private static final int[] POSSIBLE_CARD_VALUES = {0, 1, 2, 3, 5, 8, 13, 20, 40, 100};
	
	private int numberOfPlayers;
	private int playersPlayed = 0;
	
	//Author: Alma Babbitt //
	private int sessionID = -1;
	private String projectName = "";
	
	private ArrayList<Integer> cardValues = new ArrayList<>();
	
	//constructor that sets values to zero and emptys the array list
	public PlanningPokerFlow() {
		numberOfPlayers = 0;
		playersPlayed = 0;
		cardValues.clear();
	}
	//Author: Alma Babbitt
	//Getters
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public int getSessionID() {
		return sessionID;
	}
	public String getProjectName() {
		return projectName;
	}
	//Setters
	// set the number of players in the session and will return true if number is within allowed range and false otherwise
	public boolean setNumberOfPlayer(int number) {
		if(number <= MAX_PLAYERS && number >= MIN_PLAYERS) {
			numberOfPlayers = number;
			return true;
		}else {
			return false;
		}
	}
	//Author: Alma Babbitt
	public void setSessionID(int ID) {
		sessionID = ID;
	}
	public void setProjectName(String name) {
		projectName = name;
	}
	
	// give card value, check if it is an allowed value and check if the playersPlayed is less than the number of players
	// function returns the number of players left that need to give card values
	// if -1 is returned given value isn't a possible card value
	// if -2 is returned all players have already played
	public int giveCardValue(int value) {
		// Iterate through the array to check if the value is a possible value
		boolean found = false;
      for (int p_value : POSSIBLE_CARD_VALUES) {
          if (p_value == value) {
              found = true;
              break; // Value found, exit the loop
          }
      }
      if(!found) {
      	return -1;
      }
      if(playersPlayed < numberOfPlayers) {
      	playersPlayed++;
      	cardValues.add(value);
      	return numberOfPlayers - playersPlayed;
      }
      else {
      	return -2;
      }
	}
	
	// this function will return an array with the largest card value and the smallest card value ( the outliers )
	// if the two values are the same then all players were in agreement
	// if the values are a negative number then that number of people have yet to play
	public int[] checkResults() {
		
		int [] outliers = {0, 0};
		
		if(playersPlayed == numberOfPlayers) {
			// set max to the min of possible card values initially
			// set min to the max of possible card values initially
			int minValue = 100;
		    int maxValue = 0;
	
		    // iterate through the cardValues
		    for (int number : cardValues) {
		    	if (number < minValue) {
		    		minValue = number;
		    	}
	
		        if (number > maxValue) {
		        	maxValue = number;
		        }
		    }
		    outliers[0] = minValue;
		    outliers[1] = maxValue;
		}
		else {
			outliers[0] = playersPlayed - numberOfPlayers;
			outliers[1] = playersPlayed - numberOfPlayers;
		}
		return outliers;
	}
	
	// resets the number of players and cards that have been played
	public void reset() {
		numberOfPlayers = 0;
		playersPlayed = 0;
		cardValues.clear();
	}
}