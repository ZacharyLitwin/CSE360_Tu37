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

public class PlanningPokerController implements Initializable{

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
    	String sql = "UPDATE planningpoker SET lifeCycleStep = cycleStep, cardEnd = avgLabel ORDER by sessionID DESC limit 1";

		connect = database.connectDb("definitions");

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
				
				prepare = connect.prepareStatement(sql);
				prepare.setString(1, cycleStep.getAccessibleText());
				prepare.setString(2, avgLabel.getText());

				Stage stage = (Stage) endSession.getScene().getWindow();
				stage.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void newRound(ActionEvent event) {
    	m1.valueProperty().set(null);
    	m2.valueProperty().set(null);
    	m3.valueProperty().set(null);
    	m4.valueProperty().set(null);
    	m5.valueProperty().set(null);
    }

    @FXML
    void submit(ActionEvent event) {
    	int average = 0;
    	average = ((m1.getValue()+m2.getValue()+m3.getValue()+m4.getValue()+m5.getValue())/5);
    	avgLabel.setText(String.valueOf(average));
    }
    
    void setAllLifeCycles() {
    	String sql = "SELECT * FROM lifecycles";
		connect = database.connectDb("definitions");
    	try {
    		List<String> list = new ArrayList<String>();
			result = connect.createStatement().executeQuery(sql);
    		while(result.next()) {
    			list.add(new String(result.getString(2)));
			}
    		allLifeCycles = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
		        allLifeCycles[i] = list.get(i);
			}
			
    	}catch(Exception e) {e.printStackTrace();}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		m1.getItems().addAll(pokerCards);
		m2.getItems().addAll(pokerCards);
		m3.getItems().addAll(pokerCards);
		m4.getItems().addAll(pokerCards);
		m5.getItems().addAll(pokerCards);

		setAllLifeCycles();
		
		submitCards.setOnAction(this::submit);
		startRound.setOnAction(this::newRound);
		endSession.setOnAction(this::end);
	}
}
