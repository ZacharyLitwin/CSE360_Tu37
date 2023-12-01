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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PPSessionController implements Initializable{

	@FXML
    private Label avgLabel;
	   
    @FXML
    private ComboBox<String> cycleStep;

    @FXML
    private Button endSession;

    @FXML
    private ComboBox<Integer> m1;

    @FXML
    private ComboBox<Integer> m2;

    @FXML
    private ComboBox<Integer> m3;

    @FXML
    private ComboBox<Integer> m4;

    @FXML
    private ComboBox<Integer> m5;

    @FXML
    private Button startRound;

    @FXML
    private Button submitCards;

//	DATABASE TOOLS
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	
	
	private String[] allLifeCycles = null;
	private Integer[] pokerCards = {0,1,2,3,5,8,13,20,40,100};
	
    @FXML
    void end(ActionEvent event) {
		try {
	    	endSession.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("EffortConsole.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
	}
    
    @FXML
    void newRound(ActionEvent event) {
    	m1.valueProperty().set(null);
    	m2.valueProperty().set(null);
    	m3.valueProperty().set(null);
    	m4.valueProperty().set(null);
    	m5.valueProperty().set(null);
    	String sql = "INSERT INTO cyclescore (projectName, lifeCycle, avg) VALUES (?,?,?)";
		connect = database.connectDb("planningpoker");
		if(cycleStep.getValue() != null || avgLabel.getText() != null) {
			try {
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, PPStartController.projectName);
					prepare.setString(2, cycleStep.getValue());
					prepare.setString(3, avgLabel.getText());
					prepare.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Alert alert;
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Nice Message");
			alert.setHeaderText(null);
			alert.setContentText("Round concluded");
			alert.show();
		}else {
			Alert alert;
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Enter all blank fields");
			alert.show();
		}
    }

    @FXML
    void submit(ActionEvent event) {
    	int average = 0;
    	average = ((m1.getValue()+m2.getValue()+m3.getValue()+m4.getValue()+m5.getValue())/5);
    	avgLabel.setText(String.valueOf(average));
    }
        
    void setCBox(ComboBox<String> cb) {
    	String sql = "SELECT name FROM lifecycles";
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

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		m1.getItems().addAll(pokerCards);
		m2.getItems().addAll(pokerCards);
		m3.getItems().addAll(pokerCards);
		m4.getItems().addAll(pokerCards);
		m5.getItems().addAll(pokerCards);

		setCBox(cycleStep);
		
		submitCards.setOnAction(this::submit);
		startRound.setOnAction(this::newRound);
		endSession.setOnAction(this::end);
	}
}