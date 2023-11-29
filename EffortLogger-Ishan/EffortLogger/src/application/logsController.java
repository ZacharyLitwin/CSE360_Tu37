package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class logsController{
	@FXML 
	private ComboBox<String> projectComboBox;
	
	@FXML
	private Button loadBtn;
	
	@FXML
	private TableView<effortEntry> effortTable;
	
	@FXML
	private TableColumn<effortEntry, Integer> effortID;
	
	@FXML
	private TableColumn<effortEntry, Date> effortDate;
	
	@FXML
	private TableColumn<effortEntry, Time> effortStart;
	
	@FXML
	private TableColumn<effortEntry, Time> effortStop;
	
	@FXML
	private TableColumn<effortEntry, Time> effortTime;
	
	@FXML
	private TableColumn<effortEntry, String> effortLifeCycle;
	
	@FXML
	private TableColumn<effortEntry, String> effortCategory;
	
	@FXML
	private TableColumn<effortEntry, String> effortDetails;
	
	@FXML
	private TableView<defectEntry> defectTable;
	
	@FXML
	private TableColumn<defectEntry, Integer> defectID;
	
	@FXML
	private TableColumn<defectEntry, String> defectName;
	
	@FXML
	private TableColumn<defectEntry, String> defectDetail;
	
	@FXML
	private TableColumn<defectEntry, String> defectInjected;
	
	@FXML
	private TableColumn<defectEntry, String> defectRemoved;
	
	@FXML
	private TableColumn<defectEntry, String> defectCategory;
	
	@FXML
	private TableColumn<defectEntry, String> defectFix;
	
	@FXML
	private TableColumn<defectEntry, String> defectStatus;
	
	@FXML 
	private Button homebtn;
	
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	
	private ObservableList<effortEntry> effortEntries = FXCollections.observableArrayList();
	private ObservableList<defectEntry> defectEntries = FXCollections.observableArrayList();
	String projectName;
	
	private void getDefectData(String projectName) {
    	connect = database.connectDb("empdb");
    	String sqlDefect = "SELECT * FROM defects WHERE projectName = '" + projectName + "'";
    	
    	try {
        	prepare = connect.prepareStatement(sqlDefect);
        	result = prepare.executeQuery();
        	while(result.next()) {
				defectEntries.add(new defectEntry(result.getInt(1), 
												result.getString(2), 
												result.getString(3), 
												result.getString(4), 
												result.getString(5), 
												result.getString(6), 
												result.getString(7), 
												result.getString(8), 
												result.getString(9)));
        	}
	   	}catch(Exception e) {e.printStackTrace();}    	
    }
	
	private void getEffortData(String projectName) {
    	connect = database.connectDb("empdb");
    	String sqlEffort = "SELECT * FROM effort_entries WHERE projectName = '" + projectName + "'";    	
    	try {
        	prepare = connect.prepareStatement(sqlEffort);
        	result = prepare.executeQuery();
        	while(result.next()) {
				effortEntries.add(new effortEntry(result.getInt(1), 
												result.getString(2), 
												result.getString(3), 
												result.getString(4), 
												result.getString(5), 
												result.getString(6), 
												result.getString(7),
												result.getString(8)));
        	}
	   	}catch(Exception e) {e.printStackTrace();}    	
    }
	
	@FXML
	void loadProjects(MouseEvent e) {
		String sql = "SELECT name FROM projects";
		connect = database.connectDb("definitions");
    	try {
    		ObservableList<String> array = FXCollections.observableArrayList();
			result = connect.createStatement().executeQuery(sql);
    		while(result.next()) {
    			array.add(new String(result.getString(1)));
			}
    		projectComboBox.setItems(array);
    	}catch(Exception ex) {ex.printStackTrace();}
		finally{
    		if (result != null) {
    	        try {
    	            result.close();
    	        } catch (SQLException ex) {ex.addSuppressed(null);}
    	    }
    	    if (prepare != null) {
    	        try {
    	            prepare.close();
    	        } catch (SQLException ex) { ex.addSuppressed(null);}
    	    }
    	    if (connect != null) {
    	        try {
    	            connect.close();
    	        } catch (SQLException ex) { ex.addSuppressed(null);}
    	    }
		}
	}
	
	@FXML
	void loadBothTables() {
		if (projectComboBox.getItems().isEmpty()) {
			createAlert("Error", "There are no projects");
		} else {
			defectTable.getItems().clear();
			effortTable.getItems().clear();
			projectName = projectComboBox.getValue();
			
			getDefectData(projectName);
			defectID.setCellValueFactory(new PropertyValueFactory<defectEntry, Integer>("defectID"));
			defectName.setCellValueFactory(new PropertyValueFactory<defectEntry, String>("name"));
			defectDetail.setCellValueFactory(new PropertyValueFactory<defectEntry, String>("detail"));
			defectInjected.setCellValueFactory(new PropertyValueFactory<defectEntry, String>("injected"));
			defectRemoved.setCellValueFactory(new PropertyValueFactory<defectEntry, String>("removed"));
			defectCategory.setCellValueFactory(new PropertyValueFactory<defectEntry, String>("category"));
			defectFix.setCellValueFactory(new PropertyValueFactory<defectEntry, String>("status"));
			defectStatus.setCellValueFactory(new PropertyValueFactory<defectEntry, String>("fix"));  
			defectTable.setItems(defectEntries);
	        
			getEffortData(projectName);
			effortID.setCellValueFactory(new PropertyValueFactory<effortEntry, Integer>("effortID"));
			effortDate.setCellValueFactory(new PropertyValueFactory<effortEntry, Date>("date"));
			effortStart.setCellValueFactory(new PropertyValueFactory<effortEntry, Time>("start"));
			effortStop.setCellValueFactory(new PropertyValueFactory<effortEntry, Time>("end"));
			effortTime.setCellValueFactory(new PropertyValueFactory<effortEntry, Time>("delta"));
			effortLifeCycle.setCellValueFactory(new PropertyValueFactory<effortEntry, String>("lifeCycleStep"));
			effortCategory.setCellValueFactory(new PropertyValueFactory<effortEntry, String>("category"));
			effortDetails.setCellValueFactory(new PropertyValueFactory<effortEntry, String>("detail"));
	        effortTable.setItems(effortEntries);

		}
	}
	
	@FXML 
	void returnEffortConsole(ActionEvent e) {
		try {
    		homebtn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("EffortConsole.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception ex) {ex.printStackTrace();}
	}
	
	void createAlert(String header, String message) {
		Alert alert;
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(header);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
}