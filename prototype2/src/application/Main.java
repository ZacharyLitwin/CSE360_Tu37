/*
 * This code pretty much creates a basic login screen with text fields for 
 * username and password, a login button, and some event handling to store the user input once the login button is pressed. 
 * The TableView is for displaying data, but it's not really used yet. It's just here for note later.
 *  
 * A test case of "user1" "user2" "password1" and "password2" is applied. If you put in anything else and hit submit, 
 * it shoots out an error screen and clears the text field.
 * A list also gets updates every time there is a login (security log feature)
 * 
 */
package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// To test
import java.util.List;
import java.util.ArrayList;


public class Main extends Application{
	private List<UserInfo> existingUsers = new ArrayList<>();
	
	@Override
	public void start(Stage LoginScreen) throws Exception{
		LoginScreen.setTitle("EffortLogger Login");
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setAlignment(Pos.CENTER);
		
	    ObservableList<String> user = FXCollections.observableArrayList();
	    TableView<String> userTbl = new TableView<>();

		// Title Screen
		Label title = new Label("EffortLogger");
		grid.add(title, 0, 0);

	    // Create the text fields.
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Add the text fields to the grid
        grid.add(usernameField, 0, 1);
        grid.add(passwordField, 0, 2);

        // Fill in the text boxes
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        grid.add(loginButton, 0, 3);
        
        Scene scene = new Scene(grid, 580, 400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // Fix later 
        LoginScreen.setScene(scene);
        LoginScreen.show();
        
        // A test case
        existingUsers.add(new UserInfo("user1", "password1"));
        existingUsers.add(new UserInfo("user2", "password2"));
	    
	    loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
		        String userEntry = usernameField.getText();
		        String passEntry = passwordField.getText();
		        
		        // Check for test cases
		        boolean found = false;
                for (UserInfo userInfo : existingUsers) {
                    if (userInfo.getUsername().equals(userEntry) && userInfo.getPassword().equals(passEntry)) {
                        found = true;
                        break;
                    }
                }
		        
                if (found) { 
                    // Update the table view with the observable list
                	user.add(userEntry);
    		        user.add(passEntry);
                    userTbl.setItems(user);
                } else {
                    displayErrorScreen();
                    passwordField.clear();
                    usernameField.clear();
                }
            }
	    });
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void displayErrorScreen() {
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setContentText("Invalid username or password.");
        alert.showAndWait();
    }
}

// Test just this file
class UserInfo {
    private String username;
    private String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
