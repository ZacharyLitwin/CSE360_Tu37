//Author: Alma Babbit
// Group: TU-37
//Purpose: Controls the start screen of planning poker

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PPStartController implements Initializable{

	@FXML
	private Button beginSession;

	@FXML
	private Button exitWindow;

	@FXML
	private ComboBox<String> projectSelect;

	@FXML
	private TextArea whatWeKnow;

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	
	public static String projectName = null;

	@FXML
	void exit(ActionEvent event) {
		try {
	    	exitWindow.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("EffortConsole.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
	}

	void setCBox(ComboBox<String> cb) {
    	String sql = "SELECT name FROM projects";
		connect = database.connectDb("definitions");
    	try {
    		ObservableList<String> array = FXCollections.observableArrayList();
			result = connect.createStatement().executeQuery(sql);
    		while(result.next()) {	
    			array.add(new String(result.getString(1)));
			}
    		cb.setItems(array);
    	}catch(Exception e) {e.printStackTrace();}
	 }

	@FXML
	void startSession(ActionEvent event) {

		String sql = "INSERT INTO session(projectName,whatWeKnow) values (?,?);";

		connect = database.connectDb("planningpoker");

		try {

			if (!whatWeKnow.getText().isEmpty() || projectSelect.getValue() != null) {
				prepare = connect.prepareStatement(sql);
				prepare.setString(1, projectSelect.getValue());
				prepare.setString(2, whatWeKnow.getText());
				prepare.executeUpdate();
				
				projectName = projectSelect.getValue();
				
				beginSession.getScene().getWindow().hide();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("PlanningPoker.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);

				stage.setScene(scene);
				stage.show();
			}else {
				Alert alert;
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Enter all blank fields");
				alert.show();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		setCBox(projectSelect);
	}
}
