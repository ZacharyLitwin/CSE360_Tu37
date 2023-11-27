//Author: Zachary Litwin
package prototype;
import java.util.ArrayList;
import java.util.Scanner; // only used in main testing

public class PlanningPokerFlow {
	
	// sets default min and max number of players
	private static final int MAX_PLAYERS = 10;
	private static final int MIN_PLAYERS = 2;
	
	// sets the allowed card values
	private static final int[] POSSIBLE_CARD_VALUES = {0, 1, 2, 3, 5, 8, 13, 20, 40, 100};
	
	private int numberOfPlayers;
	private static Project project;
	private int playersPlayed = 0;
	private static PlanningPokerFlow pokerGame;
	private ArrayList<Integer> cardValues = new ArrayList<>();
	private static UserInfo user;
	
	
	//constructor that sets values to zero and emptys the array list
	public PlanningPokerFlow(Project project) {
		numberOfPlayers = 0;
		playersPlayed = 0;
		this.project = project;
		cardValues.clear();
	}
	
	// set the number of players in the session and will return true if number is within allowed range and false otherwise
	public boolean setNumberOfPlayer(int number) {
		if(number <= MAX_PLAYERS && number >= MIN_PLAYERS) {
			numberOfPlayers = number;
			return true;
		}else {
			return false;
		}
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
	
	// main used for testing functions of PlanningPokerFlow
	public static boolean simulatePlanningPoker(UserInfo user1) {
		pokerGame = new PlanningPokerFlow(project);
		if (user1.getRoles() == Authorization.userRoles.THIRDPARTY) {
			System.out.println("You are not authorized to participate in planning poker");
			return false;
		}
		String userStory;
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        System.out.println("Currently Discussiing:" + project.get_efforts_entry(0).get_effort_category());
        System.out.println("For lifeCycle: " + project.get_efforts_entry(0).get_lifeCycle());
        

        while (!quit) {
        	System.out.println("Enter a user story you would like to discuss: ");
            userStory = scanner.nextLine();
            System.out.println("Choose an option:");
            System.out.println("1) Set number of players");
            System.out.println("2) Give card value");
            System.out.println("3) Check results");
            System.out.println("4) Reset");
            System.out.println("5) Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the number of players:");
                    int numPlayers = scanner.nextInt();
                    boolean success = pokerGame.setNumberOfPlayer(numPlayers);
                    if (success) {
                        System.out.println("Number of players set to " + numPlayers);
                    } else {
                        System.out.println("Invalid number of players. Please choose between " +
                                MIN_PLAYERS + " and " + MAX_PLAYERS);
                    }
                    break;

                case 2:
                    System.out.println("Enter your card value:");
                    int cardValue = scanner.nextInt();
                    int playersLeft = pokerGame.giveCardValue(cardValue);
                    if (playersLeft >= 0) {
                        System.out.println("Card value accepted. " + playersLeft + " players left to give card values.");
                    } else if (playersLeft == -1) {
                        System.out.println("Invalid card value. Please choose one of the allowed values.");
                    } else {
                        System.out.println("All players have already played. Cannot accept more card values.");
                    }
                    break;

                case 3:
                    int[] results = pokerGame.checkResults();
                    if (results[0] >= 0) {
                        System.out.println("Minimum Card: " + results[0]);
                        System.out.println("Maximum Card: " + results[1]);
                    } else {
                        System.out.println("Not all players have played yet. " +
                                (-results[0]) + " players still need to give card values.");
                    }
                    break;

                case 4:
                    pokerGame.reset();
                    System.out.println("Game has been reset.");
                    break;

                case 5:
                    quit = true;
                    System.out.println("Quitting the game.");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 5.");
            }
            System.out.println("");
        }

        scanner.close();
        return true;
    }
}
