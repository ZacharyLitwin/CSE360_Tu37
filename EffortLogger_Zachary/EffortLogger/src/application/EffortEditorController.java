// Author: Zachary Litwin
// Group: TU-37
package application;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.EffortEditorController.Entry;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EffortEditorController {
	
	// this represents the expected input of the date text field
	private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
	// Define the date format that matches your input string
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	// this represents the expected input of the date text field
    private static final String TIME_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
    // Define the time format that matches your input string
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    
    // boolean function that checks if a input is of the correct date pattern
    public static boolean isValidDate(String date) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
	// boolean function that checks if a input is of the correct time pattern
    public static boolean isValidTime(String time) {
        Pattern pattern = Pattern.compile(TIME_PATTERN);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }
	
	// redefined class used to hold entry data
	class Entry
	{
		public Integer entriesID;
		public String projectName;
	    public String date; 
	    public String startTime;  
	    public String stopTime;  
	    public String lifeCycleStep;
	    public String category;
	    public String detail;
	    
	    public String toString() {
			return entriesID + ". " + date +" ( "+ startTime +"-"+ stopTime +" ) "+ lifeCycleStep +"; "+ category +"; "+ detail;
	    }
	 };
	 
	 // this will hold all of the entries
	 ArrayList<Entry> entryList = new ArrayList<Entry>();
	 
	 ArrayList<String> entriesIDs = new ArrayList<String>();
	 ArrayList<String> dates = new ArrayList<String>();
	 ArrayList<String> startTimes = new ArrayList<String>();
	 ArrayList<String> stopTimes = new ArrayList<String>();
	 ArrayList<String> lifeCycleSteps = new ArrayList<String>();
	 ArrayList<String> categorys = new ArrayList<String>();
	 ArrayList<String> details = new ArrayList<String>();
	 ArrayList<String> projectNames = new ArrayList<String>();
	 
	 // holds the toStrings() of entries that should be displayed in the entry combo box
	 ObservableList<String> entriesList = FXCollections.observableArrayList();
	 
	 // boolean used to track if a change to an entry has happened. initially false.
	 boolean entryChanged = false;
	 // boolean used to temporarily ignore listeners
	 private boolean ignoreListener = false;
	 
	
	//	DATABASE TOOLS
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	
	// text
	@FXML
    private Text attributesChangesSaved;
    @FXML
    private Text effortLogEntryText;
    @FXML
    private Text otherDetailText;

    // all of the combo boxes
    @FXML
    private ComboBox<String> projects;
    @FXML
    private ComboBox<String> categories;
    @FXML
    private ComboBox<String> entries;
    @FXML
    private ComboBox<String> lifecycles;
    @FXML
    private ComboBox<String> detailBox;
    

    // all of the buttons
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
    
    // the text fields
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField stopTimeField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField otherField;
    
    // pop up box to warn users about bad input
    Alert alert;
    
    //-----------------------------------------------------------------------------------
    // project and entry selection combo boxes
    //-----------------------------------------------------------------------------------
    
    // Once a project is selected the entry combo box should be enabled and should be 
    // and should be populated with all entries that match the project name.
    @FXML
    void projectSelected(ActionEvent event) {
    	// populate the entry list with the matching entries
    	entrylistmaker();
    	entries.setItems(entriesList);	
    	
    	
    	// enable the next controls to access
    	entries.setDisable(false);
        clearBtn.setDisable(false);
    }
    
    // once an entry is selected the rest of the options for changing entries should be enabled.
    @FXML
    void entrySelected(ActionEvent event) {
    	ignoreListener = true;
    	    
        // find the selected entry from the entry list
        Entry selectedEntry = new Entry();
		for(int i = 0; i < entryList.size(); i++) {
    		if(entryList.get(i).toString().equals(entries.getValue())) {
    			selectedEntry = entryList.get(i);
    		}
		}
    	
    	// enable the next controls and set intial values from selected Entry
		 lifecycles.setDisable(false);
	     lifecycles.setValue(selectedEntry.lifeCycleStep);
	        
        categories.setDisable(false);
        categories.setValue(selectedEntry.category);
        
        if(selectedEntry.category.equals("Others")) {
        	otherField.setText(selectedEntry.detail);
        	otherField.setDisable(false);
    	    otherField.setVisible(true);
    	    otherDetailText.setVisible(true);
        }
        
        else {
        	 detailBox.setDisable(false);
             detailBox.setValue(selectedEntry.detail);
        }
        
        stopTimeField.setDisable(false);
        stopTimeField.setText(selectedEntry.stopTime);
        
        startTimeField.setDisable(false);
        startTimeField.setText(selectedEntry.startTime);
        
        dateField.setDisable(false);
        dateField.setText(selectedEntry.date);
        
        splitBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        
        ignoreListener = false;
    }

    
    //-----------------------------------------------------------------------------------
    // items that change the values in an entry
    //-----------------------------------------------------------------------------------
    
    // the info in the date text field was changed. if the input passes a format check then
    // update the entryChanged boolean and the text of the attributesChangesSaved text
    @FXML
    void dateChanged(ActionEvent event) {
    	if(!ignoreListener) {
	    	entryChanged = true;
	    	attributesChangesSaved.setText("These Attributes have not been saved");
	    	attributesChangesSaved.setFill(Color.RED);
    	}

    }
    
    // the info in the start time text field was changed. if the input passes a format check then
    // update the entryChanged boolean and the text of the attributesChangesSaved text
    @FXML
    void StartTimeChanged(ActionEvent event) {
    	if(!ignoreListener) {
	    	entryChanged = true;
	    	attributesChangesSaved.setText("These Attributes have not been saved");
	    	attributesChangesSaved.setFill(Color.RED);
    	}

    }
    
    // the info in the stop time text field was changed. if the input passes a format check then
    // update the entryChanged boolean and the text of the attributesChangesSaved text
    @FXML
    void stopTimeChanged(ActionEvent event) {
    	if(!ignoreListener) {
	    	entryChanged = true;
	    	attributesChangesSaved.setText("These Attributes have not been saved");
	    	attributesChangesSaved.setFill(Color.RED);
    	}

    }
    
    // if the category combo box is changed update entryChanged boolean and and the text 
    // of the attributesChangesSaved text. If time add logic to determine if combo was actually changed
    @FXML
    void categoryChanged(ActionEvent event) {
    	if(!ignoreListener) {
	    	entryChanged = true;
	    	attributesChangesSaved.setText("These Attributes have not been saved");
	    	attributesChangesSaved.setFill(Color.RED);
    	}
		if(categories.getValue() != null) {
			// sets the enables and visibilities of the context dependent combo box 
	    	if(categories.getValue().equals("Plans")) {
	    		System.out.println("Plans");
	    		setCBox(detailBox, "plans");
	    		detailBox.setDisable(false);
	    		otherField.setDisable(true);
	    	    otherField.setVisible(false);
	    	    otherDetailText.setVisible(false);
	
	    	}
	    	else if(categories.getValue().equals("Interruptions")) {
	    		System.out.println("Interruptions");
	    		setCBox(detailBox, "interruptions");
	    		detailBox.setDisable(false);
	    		otherField.setDisable(true);
	    	    otherField.setVisible(false);
	    	    otherDetailText.setVisible(false);
	
	    	}
	    	else if(categories.getValue().equals("Deliverables")) {
	    		System.out.println("deliverables");
	    		setCBox(detailBox, "deliverables");
	    		detailBox.setDisable(false);
	    		otherField.setDisable(true);
	    	    otherField.setVisible(false);
	    	    otherDetailText.setVisible(false);
	
	    	}
	    	else if(categories.getValue().equals("Defects")) {
	    		System.out.println("Defects");
	    		setCBox(detailBox, "defects");
	    		detailBox.setDisable(false);
	    		otherField.setDisable(true);
	    	    otherField.setVisible(false);
	    	    otherDetailText.setVisible(false);
	    	}
	    	else if(categories.getValue().equals("Others")) {
	    		System.out.println("Others");
	    		otherField.setDisable(false);
	    	    otherField.setVisible(true);
	    	    otherDetailText.setVisible(true);
	    		
	    	    detailBox.setValue(null);
	    	    detailBox.setDisable(true);
			}
    	}
    }
    
    // if the life cycle combo box is changed update entryChanged boolean and and the text 
    //of the attributesChangesSaved text. If time add logic to determine if combo was actually changed
    @FXML
    void lifeCycleChanged(ActionEvent event) {
    	if(!ignoreListener) {
	    	entryChanged = true;
	    	attributesChangesSaved.setText("These Attributes have not been saved");
	    	attributesChangesSaved.setFill(Color.RED);
    	}
    }
    
    // if the plan/deliverable/interruption/other combo box is changed update entryChanged boolean and and the text 
    //of the attributesChangesSaved text. If time add logic to determine if combo was actually changed
    @FXML
    void detailsChanged(ActionEvent event) {
    	if(!ignoreListener) {
	    	entryChanged = true;
	    	attributesChangesSaved.setText("These Attributes have not been saved");
	    	attributesChangesSaved.setFill(Color.RED);
    	}
    }
    
    //-----------------------------------------------------------------------------------
    // all of the button action handlers
    //-----------------------------------------------------------------------------------
    
    // Will delete all of the effort entries that are a part of the the selected project
    @FXML
    void clearLogBtn(ActionEvent event) {
    	System.out.println("Clear log button was pressed");
    	deleteAllEntries(projects.getValue());
    	entrylistmaker();
    	entries.setItems(entriesList);	
    	return;
	}
    
    
    // Will delete the currently selected entry. Should display a message if deleted or
    // if no entry is selected
    @FXML
    void deleteEntryBtn(ActionEvent event) {
    	for(int i = 0; i < entryList.size(); i++) {
    		if(entryList.get(i).toString().equals(entries.getValue())) {
    			// delete the matching entry
    			System.out.println("Found matching Entry");
    			deleteEntry(entryList.get(i), "empdb");
    			// repopulate the entry list with the updated entries
    			ignoreListener = true;
    	    	entrylistmaker();
    	    	entries.setItems(entriesList);
    	    	ignoreListener = false;
    	    	break;
    		}
    	}
    }
    
    // Changes the scene to the Effort Log screen.
    @FXML
    void proceedToLogBtn(ActionEvent event) {
    	try {
    		toLogBtn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("EffortConsole.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    // Splits a entry by getting the original's info duplicating it, adjusting the times
    // then deleting the original
    @FXML
    void splitEntryBtn(ActionEvent event) {
    	alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		alert.setContentText("Not yet implemented");
		alert.show();	
    }
    
    
    
    // gets the changed values from the effort entry info combo boxes and the times from the 
    // text fields. Should check what was entered in the text fields and display a message if 
    // entered info is not valid
    @FXML
    void updateEntryBtn(ActionEvent event) {
    	String errorMessage = "no error";
    	boolean wasError = false;
    	// check if the input of the text fields is valid
    	boolean validDate = isValidDate(dateField.getText());
    	boolean validStartTime = isValidTime(startTimeField.getText());
    	boolean validStopTime = isValidTime(stopTimeField.getText());
    	
    	// if all three are valid then update the entry
    	if(!entryChanged) {
    		errorMessage = "Entry hasn't been changed";
			wasError = true;
    	}
    	else if(!validDate) {
    		errorMessage = "Date not vaild";
			wasError = true;
    	}
    	else if(!validStartTime) {
    		errorMessage = "start time not valid";
			wasError = true;
    	}
    	else if(!validStopTime) {
    		errorMessage = "end time not valid";
			wasError = true;
    	}
    	else if(lifecycles.getValue() == null) {
    		errorMessage = "No life cycle step selected";
			wasError = true;
    	}
    	else if(categories.getValue() == null) {
			errorMessage = "No category selected";
			wasError = true;
		}
    	else if(categories.getValue().equals("Others") && otherField.getText().isEmpty()) {
    		errorMessage = "Other test field is blank";
			wasError = true;
    	}
    	else if(!categories.getValue().equals("Others") &&  detailBox.getValue() == null) {
    		errorMessage = "No detail selected";
			wasError = true;
    	}
    	
    	if(wasError) {
    		alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText(errorMessage);
			alert.show();	
    	}
    	else{
    		// temp Entry that we can modify and insert back into the list
    		Entry tempEntry = new Entry();
    		
    		// delete the orginal entry and initialize the temp entry
    		for(int i = 0; i < entryList.size(); i++) {
        		if(entryList.get(i).toString().equals(entries.getValue())) {
        			// delete the matching entry
        			System.out.println("Found matching Entry");
        			tempEntry = entryList.get(i);
        			deleteEntry(entryList.get(i), "empdb");
        		}
        	}
    		
    		// these were already checked
    		tempEntry.date = dateField.getText();
    		tempEntry.startTime = startTimeField.getText();
    		tempEntry.stopTime = stopTimeField.getText();	
			tempEntry.lifeCycleStep = lifecycles.getValue();
    		tempEntry.category = categories.getValue();
    		if(tempEntry.category.equals("Others")) {
    			tempEntry.detail = otherField.getText();
    		}
    		else {
    			tempEntry.detail = detailBox.getValue();
        	}   	
    		
    		// insert the new entry into the database
    		replaceEntry(tempEntry);
    		
    		ignoreListener = true;
    		
    		projects.setValue(null);
	        
    		entries.setDisable(true);
	        categories.setDisable(true);
	        lifecycles.setDisable(true);
	        detailBox.setDisable(true);
	        
	        stopTimeField.setDisable(true);
	        startTimeField.setDisable(true);
	        dateField.setDisable(true);
	        splitBtn.setDisable(true);
	        updateBtn.setDisable(true);
	        deleteBtn.setDisable(true);
	        clearBtn.setDisable(true);
	        
	        otherField.setDisable(true);
	        otherField.setVisible(false);
	        otherDetailText.setVisible(false);
	        
	     // reset the ignore listener flag
	    	ignoreListener = false;
        
	        // reset the changes saved flag
	        entryChanged = false;
	        attributesChangesSaved.setText("These Attributes have been saved");
	    	attributesChangesSaved.setFill(Color.BLACK);
    	}
    }

    //-----------------------------------------------------------------------------------
    // Runs at the screen's startup
    //-----------------------------------------------------------------------------------
    @FXML
    void initialize() {
        assert attributesChangesSaved != null : "fx:id=\"attributesChangesSaved\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert categorys != null : "fx:id=\"categories\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert effortLogEntryText != null : "fx:id=\"effortLogEntryText\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert entries != null : "fx:id=\"entries\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert lifecycles != null : "fx:id=\"lifecycles\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert detailBox != null : "fx:id=\"plans\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert projects != null : "fx:id=\"projects\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert splitBtn != null : "fx:id=\"splitBtn\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert toLogBtn != null : "fx:id=\"toLogBtn\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'EffortEditor.fxml'.";
        
        // sets initial enables and visibilities 
        entries.setDisable(true);
        categories.setDisable(true);
        lifecycles.setDisable(true);
        detailBox.setDisable(true);
        
        stopTimeField.setDisable(true);
        startTimeField.setDisable(true);
        dateField.setDisable(true);
        splitBtn.setDisable(true);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        clearBtn.setDisable(true);
        
        otherField.setDisable(true);
        otherField.setVisible(false);
        otherDetailText.setVisible(false);
        
        // sets the combo box values based on values in SQL
    	setCBox(categories, "categories");
    	setCBox(projects, "projects");
    	setCBox(lifecycles, "lifecycles");

    }
    
    // function written by Alma Babbit
    //-----------------------------------------------------------------------------------
    // function that populates a comboBox with data from SQL database.
    //-----------------------------------------------------------------------------------
    // any table and combo box must have the same name and the SQL table must have a column 
    // called name
	void setCBox(ComboBox<String> cb, String table_name) {
		String sql = "SELECT name FROM " + table_name;
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
	
	//-----------------------------------------------------------------------------------
    // function that populates an array list with values from the SQL table column name
    //-----------------------------------------------------------------------------------
	// toString format = 1. date ( time - time) lifeCycleStep; Effort Category: Details
	
	void setArrayList(ArrayList<String> list, String name, String table) {
		// make sure array list is empty
		list.clear();
		String sql = "SELECT "+ name +" FROM " + table;
		connect = database.connectDb("empdb");
		try {
			result = connect.createStatement().executeQuery(sql);
			while(result.next()) {	
				list.add(new String(result.getString(1)));
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
	
	void entrylistmaker() {
		entriesList.clear();
		
		// initializes the array lists to have all info for entry construction
    	setArrayList(entriesIDs,"entriesID","effort_entries");
    	setArrayList(projectNames,"projectName","effort_entries");
    	setArrayList(dates,"date","effort_entries");
    	setArrayList(startTimes,"startTime","effort_entries");
    	setArrayList(stopTimes,"stopTime","effort_entries");
    	setArrayList(lifeCycleSteps,"lifeCycleStep","effort_entries");
    	setArrayList(categorys,"category","effort_entries");
    	setArrayList(details,"detail","effort_entries");
    	
		for(int i = 0; i < dates.size(); i++) {
			Entry entry = new Entry();
			entry.entriesID = Integer.valueOf(entriesIDs.get(i));
			entry.projectName = projectNames.get(i);
			entry.date = dates.get(i);
			entry.startTime = startTimes.get(i);
			entry.stopTime = stopTimes.get(i);		
			entry.lifeCycleStep = lifeCycleSteps.get(i);
			entry.category = categorys.get(i);
			entry.detail = details.get(i);
			entryList.add(entry);
			if(entry.projectName.equals(projects.getValue())) {
				entriesList.add(entry.toString());
			}
		}
		// updates the message under the project combo box to show the number of entries in a project
		effortLogEntryText.setText(entriesList.size() + " effort log entries for this project");
	}
	
	//-----------------------------------------------------------------------------------
	// function that deletes an entry from the SQL database.
    //-----------------------------------------------------------------------------------
    // Takes in an entry object that is in the entry list.
	// I don't know why this mess works but it does
		
		void deleteEntry(Entry entryToDelete, String name) {
			connect = database.connectDb("empdb");
		      String query = "delete from effort_entries where entriesID = ?";
		      PreparedStatement preparedStmt = null;
			try {
				preparedStmt = connect.prepareStatement(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		      try {
				preparedStmt.setInt(1, entryToDelete.entriesID);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				preparedStmt.execute();
			}catch(Exception e) {e.printStackTrace();}
	    }
		
	//-----------------------------------------------------------------------------------
	// function that inserts an entry to the SQL database.
    //-----------------------------------------------------------------------------------
    // Takes in an entry object that is in the entry list.
	// I don't know why this mess works but it does
		
		void replaceEntry(Entry entryToInsert) {
			connect = database.connectDb("empdb");
		      String query = " insert into effort_entries (entriesID, projectName, date, startTime, stopTime, lifeCycleStep, category, detail)"
		    	        + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		      PreparedStatement preparedStmt = null;
			try {
				preparedStmt = connect.prepareStatement(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		      try {
				preparedStmt.setInt		(1, entryToInsert.entriesID);
				preparedStmt.setString	(2, entryToInsert.projectName);
				
	            java.util.Date parsedDate = dateFormat.parse(entryToInsert.date);
	            Date sqlDate = new Date(parsedDate.getTime());
			    preparedStmt.setDate  	(3, sqlDate);
			    
	            java.util.Date parsedTimeStart = timeFormat.parse(entryToInsert.startTime);
	            Time sqlTimeStart = new Time(parsedTimeStart.getTime());
			    preparedStmt.setTime	(4, sqlTimeStart);
			    
			    java.util.Date parsedTimeStop = timeFormat.parse(entryToInsert.stopTime);
	            Time sqlTimeStop = new Time(parsedTimeStop.getTime());
			    preparedStmt.setTime	(5, sqlTimeStop);
			    
			    preparedStmt.setString 	(6, entryToInsert.lifeCycleStep);
			    preparedStmt.setString 	(7, entryToInsert.category);
			    preparedStmt.setString 	(8, entryToInsert.detail);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}

			try {
				preparedStmt.execute();
			}catch(Exception e) {e.printStackTrace();}
	    }
		
	//-----------------------------------------------------------------------------------
	// function that deletes all the entries for a project
    //-----------------------------------------------------------------------------------
    // Takes in an entry object that is in the entry list.
	// SQL notation:   DELETE FROM `definitions`.`entries` WHERE (`projectName` = '?');
		void deleteAllEntries(String projectname) {
			connect = database.connectDb("empdb");
			String query = "delete from effort_entries where ( projectName = ? )";
			 PreparedStatement preparedStmt = null;
			try {
				preparedStmt = connect.prepareStatement(query);
				preparedStmt.setString	(1, projectname);
				preparedStmt.execute();
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