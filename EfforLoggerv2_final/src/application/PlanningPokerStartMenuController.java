//Author: Trevor Huss
// Group: TU-37
//Purpose: Controls the Planning poker start Screen

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PlanningPokerStartMenuController implements Initializable{

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
	private String[] allProjects = null;
	
	 @FXML
	    void initialize() {
		 selectProjects();
	 }
	
	@FXML
	void select(ActionEvent event) {
		System.out.println("A project was selected");
	}

	@FXML
	void exit(ActionEvent event) {
		Stage stage = (Stage) exitWindow.getScene().getWindow();
		stage.close();
	}

	@FXML
	void selectProjects() {
		String sql = "SELECT name FROM projects";
		connect = database.connectDb("definitions");
		try {
			ObservableList<String> list = FXCollections.observableArrayList();
			result = connect.createStatement().executeQuery(sql);
			while(result.next()) {
				list.add(new String(result.getString(1)));
			}
			allProjects = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				allProjects[i] = list.get(i);
			}
			projectSelect.setItems(list);

		}catch(Exception e) {e.printStackTrace();}
	}

	@FXML
	void startSession(ActionEvent event) {

		String sql = "INSERT INTO planningpoker(projectName,whatWeKnow) values (?,?);";

		connect = database.connectDb("definitions");

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			Alert alert;

			if (whatWeKnow.getText() != null) {
				
				prepare = connect.prepareStatement(sql);
				prepare.setString(1, projectSelect.getAccessibleText());
				prepare.setString(2, whatWeKnow.getText());

				beginSession.getScene().getWindow().hide();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("PlanningPokerController.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);

				stage.setScene(scene);
				stage.show();
			}else {
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
		selectProjects();
		beginSession.setOnAction(this::startSession);
		exitWindow.setOnAction(this::exit);

	}
}
