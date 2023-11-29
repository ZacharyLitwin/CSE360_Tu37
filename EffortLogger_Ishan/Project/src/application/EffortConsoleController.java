package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EffortConsoleController {

    @FXML
    private Button defectConsoleBtn;

    @FXML
    private Button definitionsBtn;

    @FXML
    private ComboBox<String> categories;

    @FXML
    private Button effortEditorBtn;

    @FXML
    private Button endActivityBtn;

    @FXML
    private ComboBox<String> lifecycles;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button logsBtn;

    @FXML
    private ComboBox<String> projects;

    @FXML
    private Button startActivityBtn;

    @FXML
    private ComboBox<String> plans;
    
	//	DATABASE TOOLS
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

    @FXML
    void endActivity(ActionEvent event) {
    	String sql = "SELECT * FROM definitions WHERE username = ? and password = ?";
		
		connect = database.connectDb("definitions");
		
		try {
//			prepare = connect.prepareStatement(sql);
//			prepare.setString(1, username.getText());
//			prepare.setString(2, password.getText());
//			
//			result = prepare.executeQuery();
//			Alert alert;
//						
//			if(username.getText().isEmpty() || password.getText().isEmpty()) {
//				alert = new Alert(AlertType.ERROR);
//				alert.setTitle("Error Message");
//				alert.setHeaderText(null);
//				alert.setContentText("Enter all blank fields");
//				alert.show();
//			}else {
//				if(result.next()) {					
//					loginBtn.getScene().getWindow().hide();
//					Parent root = FXMLLoader.load(getClass().getResource("EffortConsole.fxml"));
//					Stage stage = new Stage();
//					Scene scene = new Scene(root);
//					
//					stage.setScene(scene);
//					stage.show();
//					
//				}else {
//					alert = new Alert(AlertType.ERROR);
//					alert.setTitle("Error Message");
//					alert.setHeaderText(null);
//					alert.setContentText("Wrong Username/Password");
//					alert.show();
//				}
//			}
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
    void logOut(ActionEvent event) {
    	try {
    		logOutBtn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
    }

    @FXML
    void startActivityBtn(ActionEvent event) {
    	setCBox(categories);
    	setCBox(plans);
    	setCBox(projects);
    	setCBox(lifecycles);
    }
    
    @FXML
    void definition(ActionEvent event) {
    	try {
    		definitionsBtn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("ProjectView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    @FXML
    void effortEditor(ActionEvent event) {
    	try {
    		effortEditorBtn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("ProjectView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    @FXML
    void logs(ActionEvent event) {
    	try {
    		logsBtn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("ProjectView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    @FXML
    void defectConsole(ActionEvent event) {
    	try {
    		defectConsoleBtn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("defectEditor.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    void setCBox(ComboBox<String> cb) {
    	String sql = "SELECT name FROM " + cb.getId();
		connect = database.connectDb("definitions");
    	try {
    		ObservableList<String> array = FXCollections.observableArrayList();
			result = connect.createStatement().executeQuery(sql);
    		while(result.next()) {	
    			array.add(new String(result.getString(1)));
			}
    		cb.setItems(array);
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
}
