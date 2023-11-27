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
import javafx.scene.control.TextField;
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
    private Text effortLogEntryText;

    @FXML
    private ComboBox<String> categories;

    @FXML
    private ComboBox<String> entries;

    @FXML
    private ComboBox<String> lifecycles;

    @FXML
    private ComboBox<String> plans;
    
    @FXML
    private ComboBox<String> deliverables;
    
    @FXML
    private ComboBox<String> interruptions;
    
    @FXML
    private ComboBox<String> defects;
    
    @FXML
    private ComboBox<String> others;

    @FXML
    private ComboBox<String> projects;

    @FXML
    private Button splitBtn;

    @FXML
    private Button toLogBtn;

    @FXML
    private Button updateBtn;
    
    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button clearBtn;
    
    @FXML
    private TextField startTimeField;
    
    @FXML
    private TextField stopTimeField;
    
    @FXML
    private TextField dateField;
    
    
    //-----------------------------------------------------------------------------------
    // project and entry selection combo boxes
    //-----------------------------------------------------------------------------------
    
    // Once a project is selected the entry combo box should be enabled and should be 
    // and should be populated with all entries that match the project name.
    @FXML
    void projectSelected(ActionEvent event) {
    	// test message 
    	System.out.println("A Project was selected");
    	
    	// enable the next controls to access
    	entries.setDisable(false);
        clearBtn.setDisable(false);
        categories.setDisable(false);
    }
    
    // once an entry is selected the rest of the options for changing entries should be enabled.
    @FXML
    void entrySelected(ActionEvent event) {
    	// test message
    	System.out.println("An Entry was selected");
    	
    	// enable the next controls
        categories.setDisable(false);
        lifecycles.setDisable(false);
        plans.setDisable(false);
        stopTimeField.setDisable(false);
        startTimeField.setDisable(false);
        dateField.setDisable(false);
        splitBtn.setDisable(false);
        toLogBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
    }

    
    //-----------------------------------------------------------------------------------
    // items that change the values in an entry
    //-----------------------------------------------------------------------------------
    
    // the info in the date text field was changed. if the input passes a format check then
    // update the entryChanged boolean and the text of the attributesChangesSaved text
    @FXML
    void dateChanged(ActionEvent event) {
    	System.out.println("Date change Detected");
    }
    
    // the info in the start time text field was changed. if the input passes a format check then
    // update the entryChanged boolean and the text of the attributesChangesSaved text
    @FXML
    void StartTimeChanged(ActionEvent event) {
    	System.out.println("Start Time change Detected");
    }
    
    // the info in the stop time text field was changed. if the input passes a format check then
    // update the entryChanged boolean and the text of the attributesChangesSaved text
    @FXML
    void stopTimeChanged(ActionEvent event) {
    	System.out.println("Stop Time change Detected");
    }
    
    // if the category combo box is changed update entryChanged boolean and and the text 
    // of the attributesChangesSaved text. If time add logic to determine if combo was actually changed
    @FXML
    void categoryChanged(ActionEvent event) {
    	System.out.println("Category change Detected");
    	System.out.println("Selected Category is: " + categories.getValue());
    	
    // sets the enables and visibilities of the context dependent combo box 
    	if(categories.getValue().equals("Plans")) {
    		System.out.println("PLans");
    		plans.setDisable(false);
    		plans.setVisible(true);
    		
    		interruptions.setDisable(true);
	        deliverables.setDisable(true);
	        others.setDisable(true);
	        defects.setDisable(true);
	        
	        interruptions.setVisible(false);
	        deliverables.setVisible(false);
	        others.setVisible(false);
	        defects.setVisible(false);
    	}
    	else if(categories.getValue().equals("Interruptions")) {
    		System.out.println("Interruptions");
    		interruptions.setDisable(false);
    		interruptions.setVisible(true);
    		
    		plans.setDisable(true);
	        deliverables.setDisable(true);
	        others.setDisable(true);
	        defects.setDisable(true);
	        
	        plans.setVisible(false);
	        deliverables.setVisible(false);
	        others.setVisible(false);
	        defects.setVisible(false);
    	}
    	else if(categories.getValue().equals("Deliverables")) {
    		System.out.println("deliverables");
    		deliverables.setDisable(false);
    		deliverables.setVisible(true);
    		
    		plans.setDisable(true);
    		interruptions.setDisable(true);
	        others.setDisable(true);
	        defects.setDisable(true);
	        
	        plans.setVisible(false);
	        interruptions.setVisible(false);
	        others.setVisible(false);
	        defects.setVisible(false);
    	}
    	else if(categories.getValue().equals("Defects")) {
    		System.out.println("Defects");
    		defects.setDisable(false);
    		defects.setVisible(true);
    		
    		plans.setDisable(true);
    		interruptions.setDisable(true);
	        others.setDisable(true);
	        deliverables.setDisable(true);
	        
	        plans.setVisible(false);
	        interruptions.setVisible(false);
	        others.setVisible(false);
	        deliverables.setVisible(false);
    	}
    	else if(categories.getValue().equals("Others")) {
    		System.out.println("Others");
    		others.setDisable(false);
    		others.setVisible(true);
    		
    		plans.setDisable(true);
    		interruptions.setDisable(true);
    		defects.setDisable(true);
	        deliverables.setDisable(true);
	        
	        plans.setVisible(false);
	        interruptions.setVisible(false);
	        defects.setVisible(false);
	        deliverables.setVisible(false);
    	}
    }
    
    // if the life cycle combo box is changed update entryChanged boolean and and the text 
    //of the attributesChangesSaved text. If time add logic to determine if combo was actually changed
    @FXML
    void lifeCycleChanged(ActionEvent event) {
    	System.out.println("The Entry's Life Cycle Step was changed");
    }
    
    // if the plan/deliverable/interruption/other combo box is changed update entryChanged boolean and and the text 
    //of the attributesChangesSaved text. If time add logic to determine if combo was actually changed
    @FXML
    void planChanged(ActionEvent event) {
    	System.out.println("The Entry's Plan was changed");
    }
    
    @FXML
    void deliverableChanged(ActionEvent event) {
    	System.out.println("The Entry's Deliverable was changed");
    }
    
    @FXML
    void interruptionChanged(ActionEvent event) {
    	System.out.println("The Entry's Interruption was changed");
    }
    
    @FXML
    void defectChanged(ActionEvent event) {
    	System.out.println("The Entry's Defect was changed");
    }
    
    @FXML
    void otherChanged(ActionEvent event) {
    	System.out.println("The Entry's Other was changed");
    }
    
    //-----------------------------------------------------------------------------------
    // all of the button action handlers
    //-----------------------------------------------------------------------------------
    
    // Will delete all of the effort entries that are a part of the the selected project
    @FXML
    void clearLogBtn(ActionEvent event) {
    	System.out.println("Clear log button was pressed");
    }
    
    // Will delete the currently selected entry. Should display a message if deleted or
    // if no entry is selected
    @FXML
    void deleteEntryBtn(ActionEvent event) {
    	System.out.println("Delete log button was pressed");
    }
    
    // Changes the scene to the Effort Log screen.
    @FXML
    void proceedToLogBtn(ActionEvent event) {
    	System.out.println("The Proceed To Log button was pressed");
    }
    
    // Splits a entry by getting the original's info duplicating it, adjusting the times
    // then deleting the original
    @FXML
    void splitEntryBtn(ActionEvent event) {
    	System.out.println("The Split Log Button was pressed");
    }
    
    // gets the changed values from the effort entry info combo boxes and the times from the 
    // text fields. Should check what was entered in the text fields and display a message if 
    // entered info is not valid
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
        
        entries.setDisable(true);
        categories.setDisable(true);
        lifecycles.setDisable(true);
        plans.setDisable(true);
        interruptions.setDisable(true);
        deliverables.setDisable(true);
        others.setDisable(true);
        defects.setDisable(true);
        
        interruptions.setVisible(false);
        deliverables.setVisible(false);
        others.setVisible(false);
        defects.setVisible(false);
        
        stopTimeField.setDisable(true);
        startTimeField.setDisable(true);
        dateField.setDisable(true);
        splitBtn.setDisable(true);
        toLogBtn.setDisable(true);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        clearBtn.setDisable(true);
        
    	setCBox(categories);
    	setCBox(projects);
    	setCBox(lifecycles);
    	setCBox(plans);
    	setCBox(deliverables);
    	setCBox(interruptions);
    	setCBox(defects);
    	setCBox(others);

    }
    
    
    //-----------------------------------------------------------------------------------
    // function that populates a comboBox with data from SQL database.
    //-----------------------------------------------------------------------------------
    // any table and combo box must have the same name and the SQL table must have a column 
    // called name
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