package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class EffortConsoleController extends LoginController {
	
	// class used to hold entry data
	class Entry
	{
		public Integer entriesID;
		public Integer empID;;
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

	
	//-----------------------------------------------------------------------------------
    // JavaFX compoent defintions & other variables
    //-----------------------------------------------------------------------------------

	// All of the combo boxes
		@FXML
	    private ComboBox<String> projects;
	  	@FXML
	    private ComboBox<String> lifecycles;
	  	@FXML
	    private ComboBox<String> categories;
	  	@FXML
	    private ComboBox<String> details;

  	// All of the buttons
	    @FXML
	    private Button defectConsoleBtn;
	    @FXML
	    private Button definitionsBtn;
	    @FXML
	    private Button effortEditorBtn;
	    @FXML
	    private Button endActivityBtn;
	    @FXML
	    private Button logOutBtn;
	    @FXML
	    private Button logsBtn;
	    @FXML
	    private Button planningPokerBtn;
	    @FXML
	    private Button startActivityBtn;
    
    // The two texts that change while the program is running
	    @FXML
	    private Text clockStatus;
	    @FXML
	    private Text otherText;
    
    // the one textfield for defining the other detail
	    @FXML
	    private TextField otherTextField;
	    
	 // pop up box to warn users about bad input
	    Alert alert;

	// list to store the correct possible life cycle steps of a given project
	    ObservableList<String> lifeCycleList = FXCollections.observableArrayList();
    
	//	DATABASE TOOLS
		private Connection connect;
		private PreparedStatement prepare;
		private ResultSet result;
		
	// Define the date format that matches your input string
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // Define a date format
	    DateTimeFormatter formatterdate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Define the time format that matches your input string
	    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    // Define a time format (optional)
        DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm:ss");

	    
    // boolean to track if the sart activity button has been presses
	    boolean activityStarted = false;
	
    // After an activity is started these values will be updated to match the entry data
	    public String projectName = "placeholder";
	    public String date = "placeholder"; 
	    public String startTime = "placeholder";  
	    public String stopTime = "placeholder";  
	    public String lifeCycleStep = "placeholder";
	    public String category = "placeholder";
	    public String detail = "placeholder";
		
	//-----------------------------------------------------------------------------------
    // Action event handlers
    //-----------------------------------------------------------------------------------

    // activity button action handlers
	    @FXML
	    void startActivityBtn(ActionEvent event) {
	    	String errorMessage = "no error";
	    	boolean wasError = false;
	    	if(activityStarted) {
				errorMessage = "An Activity is Currently Running";
				wasError = true;
	    	}
	    	else if(projects.getValue() == null) {
				errorMessage = "No project selected";
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
	    	else if(categories.getValue().equals("Others") && otherTextField.getText().isEmpty()) {
	    		errorMessage = "Other test field is blank";
				wasError = true;
	    	}
	    	else if(!categories.getValue().equals("Others") &&  details.getValue() == null) {
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
	    		activityStarted = true;
		    	clockStatus.setText("Clock is Running");
		    	clockStatus.setFill(Color.GREEN);
		    	
	    	// all the checks were passed need to populate values of new entry
		    	projectName = projects.getValue();
	    	 // Get the current date
		        LocalDate currentDate = LocalDate.now();
	        // Format the current date as a string
		        date = currentDate.format(formatterdate);
	        // Get the current time
		        LocalTime currentTime = LocalTime.now();
	        // Format the current time as a string
		        startTime  = currentTime.format(formattertime); 
	        // to stop any potential errors set the stop time to be the same as start time
		 	    stopTime = startTime;  
		 	    lifeCycleStep = lifecycles.getValue();
		 	    category = categories.getValue();
		 	    
		 	    if(categories.getValue().equals("Others")) {
		 	    	detail = otherTextField.getText();
	    			}
	    			
	    		else {
	    			detail = details.getValue();
	    		}
    		}
	    }
	    
	    
	    @FXML
	    void endActivity(ActionEvent event) {
	    	if(activityStarted) {
	    		activityStarted = false;
		    	clockStatus.setText("Clock is Stopped");
		    	clockStatus.setFill(Color.RED);
	    	// Get the current time
		        LocalTime currentTime = LocalTime.now();
	        // Format the current time as a string
		        stopTime  = currentTime.format(formattertime); 
	    	// test make new entry and insert it
			 	   Entry newEntry = new Entry();
			    	newEntry.entriesID = 0;
			    	newEntry.empID = currentLoginID;
			    	newEntry.projectName = projectName;
			    	newEntry.date = date;
			    	newEntry.startTime = startTime;
			    	newEntry.stopTime = stopTime;
			    	newEntry.lifeCycleStep = lifeCycleStep;
			    	newEntry.category = category;
			    	newEntry.detail = detail;
			    	
			    	insertEntry(newEntry);
	    	}
	    	else {
	    		alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("No Activity has been started");
				alert.show();	
	    	}
	    }
	    
    
    // selection combo boxes action handlers
	    @FXML
	    void projectSelect(ActionEvent event) {
	    	if(projects.getValue() != null) {
		    	setLifeCycleList(lifeCycleList, projects.getValue(), "definitions");
		    	lifecycles.setItems(lifeCycleList);
		        lifecycles.setDisable(false);
	    	}
	    }
	    @FXML
	    void lifeCycleSelect(ActionEvent event) {
	    	setCBox(categories, "categories");
	    	categories.setDisable(false);
	    }
	    @FXML
	    void categorySelect(ActionEvent event) {
	    	if(categories.getValue() != null) {
	    		 details.setDisable(false);
	    		// sets the enables and visibilities of the context dependent combo box 
		    	if(categories.getValue().equals("Plans")) {
		    		System.out.println("Plans");
		    		setCBox(details, "plans");
		
		    	}
		    	else if(categories.getValue().equals("Interruptions")) {
		    		System.out.println("Interruptions");
		    		setCBox(details, "interruptions");
		
		    	}
		    	else if(categories.getValue().equals("Deliverables")) {
		    		System.out.println("deliverables");
		    		setCBox(details, "deliverables");
		
		    	}
		    	else if(categories.getValue().equals("Defects")) {
		    		System.out.println("Defects");
		    		setCBox(details, "defects");
		    	}
		    	else if(categories.getValue().equals("Others")) {
		    		System.out.println("Others");
		    		otherTextField.setDisable(false);
			    	otherTextField.setVisible(true);
			        otherText.setVisible(true);
		    	
		    	    details.setValue(null);
		    	    details.setDisable(true);
		   
		    	}
	    	}
	    }
	    @FXML
	    void detailSelect(ActionEvent event) {
	    	
	    }
	    
    // other navigation action handlers
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
	    void toDefinitions(ActionEvent event) {
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
	    void toEffortEditor(ActionEvent event) {
	    	try {
	    		effortEditorBtn.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("EffortEditor.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				
				stage.setScene(scene);
				stage.show();
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
	    @FXML
	    void toLogs(ActionEvent event) {
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
	    void toDefectConsole(ActionEvent event) {
	    	try {
	    		defectConsoleBtn.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("defectEditor.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				
				stage.setScene(scene);
				stage.show();
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
	    @FXML
	    void toPlanningPoker(ActionEvent event) {
	    	try {
	    		defectConsoleBtn.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("ProjectView.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				
				stage.setScene(scene);
				stage.show();
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
    //-----------------------------------------------------------------------------------
    // Runs at the screen's startup
    //-----------------------------------------------------------------------------------
	    @FXML
	    void initialize() {
	    	// disable and set set visiblities on screen start up
	    	otherTextField.setDisable(true);
	    	otherTextField.setVisible(false);
	        otherText.setVisible(false);
	        
	        lifecycles.setDisable(true);
	        categories.setDisable(true);
	        details.setDisable(true);
	        
	        setCBox(projects, "projects");

	    }
    
    //-----------------------------------------------------------------------------------
    // Functions for communicating with SQL database
    //-----------------------------------------------------------------------------------
    
    // Takes in an entry object that is in the entry list.
  		// I don't know why this mess works but it does
  		void insertEntry(Entry entryToInsert) {
  			connect = database.connectDb("empdb");
  		      String query = " insert into effort_entries (empID, projectName, date, startTime, stopTime, lifeCycleStep, category, detail)"
  		    	        + " values (?, ?, ?, ?, ?, ?, ?, ?)";
  		      PreparedStatement preparedStmt = null;
  			try {
  				preparedStmt = connect.prepareStatement(query);
  			} catch (SQLException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  		      try {
  		    	  	
  		    	  preparedStmt.setInt	(1, entryToInsert.empID);
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
    
  		// modified version of Alma's setCBox function that asks for a name to be included with the 
  		void setCBox(ComboBox<String> cb, String table_name) {
  			String sql = "SELECT name FROM " + table_name;
  			connect = database.connectDb("definitions");
  			try {
  				ObservableList<String> array = FXCollections.observableArrayList();
  				result = connect.createStatement().executeQuery(sql);
  				while(result.next()) {	
  					// some check to see if 
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
    // function that populates an the life cycle list 
    //-----------------------------------------------------------------------------------
  		
  		void setLifeCycleList(ObservableList<String> list, String projectName, String table) {
  			// make sure array list is empty
  			list.clear();
  			String sql = "SELECT lifeCycle FROM entries WHERE projectName = ?";
  			connect = database.connectDb("definitions");
  			try {
  				prepare = connect.prepareStatement(sql);
  				prepare.setString(1, projectName);
				result = prepare.executeQuery();
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
}
