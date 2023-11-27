package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AccountCreationController {

    @FXML
    private Button createAccountBtn;

    @FXML
    private RadioButton empBtn;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private RadioButton managerBtn;

    @FXML
    private TextField password;

    @FXML
    private Button signInBtn;

    @FXML
    private ToggleGroup status;

    @FXML
    private Button updateAccountBtn;

    @FXML
    private TextField username;

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

    @FXML
    void createAccount(ActionEvent event) {
    	String sql = "INSERT INTO login_info(username,firstname,lastname,empstatus,password) values (?,?,?,?,?);";
    		
    		connect = database.connectDb("empdb");
    		
    		try {
    			Alert alert;
    			
    			if(username.getText().isEmpty()|| firstname.getText().isEmpty()|| lastname.getText().isEmpty() || password.getText().isEmpty()) {
    				alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Error Message");
    				alert.setHeaderText(null);
    				alert.setContentText("Enter all blank fields");
    				alert.show();
    			}else {	
    				alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Account Created");
    				alert.setHeaderText(null);
    				alert.setContentText("Successful Account Creation");
    				alert.show();
    				
    				prepare = connect.prepareStatement(sql);
    				prepare.setString(1, username.getText());
    				prepare.setString(2, firstname.getText());
    				prepare.setString(3, lastname.getText());
    				if(empBtn.isSelected())
    					prepare.setString(4, empBtn.getText());
    				else
    					prepare.setString(4, managerBtn.getText());
    				prepare.setString(5, password.getText()); //This will need encryption
    				
    				prepare.executeUpdate();
    			}

    		}catch(Exception e) {e.printStackTrace();}
    		finally{
        		if (result != null) {
        	        try {
        	            result.close();
        	        } catch (SQLException e) {e.addSuppressed(null);}
        	    }
        	    if (prepare != null) {
        	        try {
        	            prepare.close();
        	        } catch (SQLException e) { e.addSuppressed(null);}
        	    }
        	    if (connect != null) {
        	        try {
        	            connect.close();
        	        } catch (SQLException e) { e.addSuppressed(null);}
        	    }
        	}
        }



    @FXML
    void signIn(ActionEvent event) {
    	try {
    		signInBtn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
    }

    @FXML
    void updateAccount(ActionEvent event) {

    }

}
