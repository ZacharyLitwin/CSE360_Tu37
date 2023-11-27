package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EffortEditorController {
	
	//	DATABASE TOOLS
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	
	@FXML
    private Text attributesChangesSaved;

    @FXML
    private ComboBox<String> categories;

    @FXML
    private Button deleteBtn;

    @FXML
    private Text effortLogEntryText;

    @FXML
    private ComboBox<String> entries;

    @FXML
    private ComboBox<String> lifecycles;

    @FXML
    private ComboBox<String> plans;

    @FXML
    private ComboBox<String> projects;

    @FXML
    private Button splitBtn;

    @FXML
    private Button toLogBtn;

    @FXML
    private Button updateBtn;
    
    
    //-----------------------------------------------------------------------------------
    // project and entry selection combo boxes
    //-----------------------------------------------------------------------------------
    @FXML
    void entrySelected(ActionEvent event) {
    	System.out.println("An Entry was selected");
    }
    @FXML
    void projectSelected(ActionEvent event) {
    	System.out.println("A Project was selected");
    }


    
    //-----------------------------------------------------------------------------------
    // items that change the values in an entry
    //-----------------------------------------------------------------------------------
    @FXML
    void dateChanged(ActionEvent event) {
    	System.out.println("Date change Detected");
    }
    @FXML
    void StartTimeChanged(ActionEvent event) {
    	System.out.println("Start Time change Detected");
    }
    @FXML
    void stopTimeChanged(ActionEvent event) {
    	System.out.println("Stop Time change Detected");
    }
    @FXML
    void categoryChanged(ActionEvent event) {
    	System.out.println("Category change Detected");
    }
    @FXML
    void lifeCycleChanged(ActionEvent event) {
    	System.out.println("The Entry's Life Cycle Step was changed");
    }
    @FXML
    void planChanged(ActionEvent event) {
    	System.out.println("The Entry's Plan was changed");
    }
    
    
    
    //-----------------------------------------------------------------------------------
    // all of the button action handlers
    //-----------------------------------------------------------------------------------
    @FXML
    void clearLogBtn(ActionEvent event) {
    	System.out.println("Clear log button was pressed");
    }
    @FXML
    void deleteEntryBtn(ActionEvent event) {
    	System.out.println("Delete log button was pressed");
    }
    @FXML
    void proceedToLogBtn(ActionEvent event) {
    	System.out.println("The Proceed To Log button was pressed");
    }
    @FXML
    void splitEntryBtn(ActionEvent event) {
    	System.out.println("The Split Log Button was pressed");
    }
    @FXML
    void updateEntryBtn(ActionEvent event) {
    	System.out.println("The Update Entry Button was pressed");
    }

    
    //-----------------------------------------------------------------------------------
    // Runs at the screen's startup
    //-----------------------------------------------------------------------------------
    @FXML
    void initialize() {
        assert attributesChangesSaved != null : "fx:id=\"attributesChangesSaved\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert categories != null : "fx:id=\"categories\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert effortLogEntryText != null : "fx:id=\"effortLogEntryText\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert entries != null : "fx:id=\"entries\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert lifecycles != null : "fx:id=\"lifecycles\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert plans != null : "fx:id=\"plans\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert projects != null : "fx:id=\"projects\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert splitBtn != null : "fx:id=\"splitBtn\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert toLogBtn != null : "fx:id=\"toLogBtn\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'EffortEditor.fxml'.";
    	setCBox(categories);
    	setCBox(projects);
    	setCBox(lifecycles);
    }
    
    
    //-----------------------------------------------------------------------------------
    // function that populates a comboBox with data from the SQL database
    //-----------------------------------------------------------------------------------
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