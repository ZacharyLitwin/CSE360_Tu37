/*
 * This code pretty much creates a basic login screen with text fields for 
 * username and password, a login button, and some event handling to store the user input once the login button is pressed. 
 * The TableView is for displaying data, but it's not really used yet. It's just here for note later.
 *  
 * A test case of "user1" "user2" "password1" and "password2" is applied. If you put in anything else and hit submit, 
 * it shoots out an error screen and clears the text field.
 * A list also gets updates every time there is a login (security log feature)
 * Author: Karryl Dumalag
 */
//Group:	Tu-37
package prototype;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.concurrent.TimeUnit;

// To test
import java.util.List;
import java.util.ArrayList;


public class Main extends Application{
	private List<UserInfo> existingUsers = new ArrayList<>();
	ObservableList<LoginAuditEntry> loginAuditEntries = FXCollections.observableArrayList();
	private int failedLoginAttempts = 0;
    private long lastFailedLoginTime = 0;
	
	@Override
	public void start(Stage LoginScreen) throws Exception {
		LoginScreen.setTitle("EffortLogger Login");
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setAlignment(Pos.CENTER);
		
	    ObservableList<String> user = FXCollections.observableArrayList();

		// Title Screen
		Label title = new Label("EffortLogger");
		grid.add(title, 0, 0);

	    // Create the text fields.
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Add the text fields to the grid
        grid.add(usernameField, 0, 1);
        grid.add(passwordField, 0, 2);

        // Placeholders for the textboxes
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        grid.add(loginButton, 0, 3);
        
        Button newAccountButton = new Button("New Account");
        newAccountButton.visibleProperty();
        grid.add(newAccountButton, 0, 4);
        
        Scene scene = new Scene(grid, 580, 400);
        LoginScreen.setScene(scene);
        LoginScreen.show();
        
        // The test cases
        existingUsers.add(new UserInfo("user1", "password1", Authorization.userRoles.EMPLOYEE));
        existingUsers.add(new UserInfo("user2", "password2", Authorization.userRoles.MANAGER));
	    existingUsers.add(new UserInfo("user3", "password3", Authorization.userRoles.THIRDPARTY));
        
	    loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if (failedLoginAttempts >= 5) {
                    long currentTime = System.currentTimeMillis();
                    long timeSinceLastFailedLogin = currentTime - lastFailedLoginTime;
                    if (TimeUnit.MILLISECONDS.toMinutes(timeSinceLastFailedLogin) < 2) {
                        displayLoginBlockedScreen();
                        return;
                    }
                }
            	String userEntry = usernameField.getText();
            	String passEntry = passwordField.getText();
		        
		        // Check for test cases
		        UserInfo current = returnUser(userEntry, passEntry);
		        boolean found = validUser(userEntry, passEntry);
		        
                loginAuditEntries.add(new LoginAuditEntry(userEntry, System.currentTimeMillis(), found));
                
                if (found) { 
                    // Update the table view with the observable list
                	user.add(userEntry);
    		        user.add(passEntry);
                    failedLoginAttempts = 0;
                    LoginScreen.close();
                    openEffortLoggerUI(LoginScreen, current);
                } 
                else {
                	failedLoginAttempts++;
                    lastFailedLoginTime = System.currentTimeMillis();
                    displayErrorScreen(current.getRoles());
                    passwordField.clear();
                }
            }
	    });
	    
	    // A new window for account creation
	    newAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage newAccountStage = new Stage();
                newAccountStage.setTitle("New Account");

                GridPane newAccountGrid = new GridPane();
                newAccountGrid.setVgap(5);
                newAccountGrid.setAlignment(Pos.CENTER);

                TextField newUsernameField = new TextField();
                PasswordField newPasswordField = new PasswordField();

                newAccountGrid.add(newUsernameField, 0, 1);
                newAccountGrid.add(newPasswordField, 0, 2);

                newUsernameField.setPromptText("New Username");
                newPasswordField.setPromptText("New Password");

                String userEntry = usernameField.getText();
            	String passEntry = passwordField.getText();
            	
            		Button createAccountButton = new Button("Create Account");
                	newAccountGrid.add(createAccountButton, 0, 3);
                

                Scene newAccountScene = new Scene(newAccountGrid, 400, 300);
                newAccountStage.setScene(newAccountScene);
                newAccountStage.show();

                createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String newUsername = newUsernameField.getText();
                        String newPassword = newPasswordField.getText();

                        if (newUsername.isEmpty() || newPassword.isEmpty()) {
                            displayErrorScreen("New Account Error", "Both username and password are required.");
                        } 
                        else if (newPassword.length() <= 10){
                        	displayErrorScreen("New Account Error", "Password must be more than 10 characters");
                        	newPasswordField.clear();
                        }
                        else {
                        	existingUsers.add(new UserInfo(newUsername, newPassword, Authorization.userRoles.EMPLOYEE));
                            newAccountStage.close();  // Close the New Account stage
                            newUsernameField.clear();
                            newPasswordField.clear();
                        }
                    }
                });
            }
        });
	}

	private void openEffortLoggerUI(Stage stage, UserInfo user) {
		EffortLoggerUI effortLoggerUI = new EffortLoggerUI(stage, user);
		effortLoggerUI.displayEffortLoggerConsole();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	private void displayErrorScreen(Authorization.userRoles role) {
		if (role == Authorization.userRoles.MANAGER) {
			displayErrorScreen("Invalid role", "You are not authorized to access the effort logger");
		}
		else {
			displayErrorScreen("Login Error", "Invalid username or password.");
        }
    }

    private void displayErrorScreen(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	private void displayLoginBlockedScreen() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Blocked");
        alert.setContentText("Too many failed attempts. Wait for 2 minutes.");
        alert.showAndWait();
    }
	
	private boolean validUser(String userEntry, String passEntry) {
		boolean found = false;
        for (UserInfo userInfo : existingUsers) {
            if (userInfo.getUserName().equals(userEntry) && userInfo.getPassword().equals(passEntry)) {
                found = true;
                break;
            }
        }
        return found;
	}
	
	private UserInfo returnUser(String userEntry, String passEntry) {
		UserInfo current = null;
        for (UserInfo userInfo : existingUsers) {
            if (userInfo.getUserName().equals(userEntry) && userInfo.getPassword().equals(passEntry)) {
                current = userInfo;
                break;
            }
        }
        return current;
	}
}

class LoginAuditEntry {
    private String username;
    private long timestamp;
    private boolean successful;

    public LoginAuditEntry(String username, long timestamp, boolean successful) {
        this.username = username;
        this.timestamp = timestamp;
        this.successful = successful;
    }

    public String getUsername() {
        return username;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isSuccessful() {
        return successful;
    }
}