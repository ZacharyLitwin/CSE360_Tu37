// Author: Ishan
// major chnages made by Zachary to add employee ID and other checks

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class defectEditorController extends LoginController{
	//-----------------------------------------------------------------------------------
	// Defect class used to hold entry data and arrayList to store them
    //-----------------------------------------------------------------------------------

		class Defect
		{
			public Integer defectID;
			public Integer empID;
			public String projectName;
		    public String defectName; 
		    public String defectDef;  
		    public String defectsInjected;  
		    public String defectsRemoved;
		    public String defectsCategory;
		    public String fix;
		    public String status;
		    
		    public String toString() {
				return defectID + ". " + defectName;
		    }
		    
		 };
	
		 ArrayList<Defect> defectList = new ArrayList<Defect>();
		 
		 ArrayList<String> defectIDs = new ArrayList<String>();
		 ArrayList<String> empIDs = new ArrayList<String>();
		 ArrayList<String> projectNames = new ArrayList<String>();
		 ArrayList<String> defectNames = new ArrayList<String>();
		 ArrayList<String> defectDefs = new ArrayList<String>();
		 ArrayList<String> defectsInjects = new ArrayList<String>();
		 ArrayList<String> defectsRemoves = new ArrayList<String>();
		 ArrayList<String> defectsCategories = new ArrayList<String>();
		 ArrayList<String> fixes = new ArrayList<String>();
		 ArrayList<String> statuses = new ArrayList<String>();
		 
		 ObservableList<String> defectDisplayList = FXCollections.observableArrayList();
	
	//-----------------------------------------------------------------------------------
    // JavaFX compoent defintions & other variables
    //-----------------------------------------------------------------------------------

	// All of the combo boxes
		@FXML
		private ComboBox<String> selectProject;
		@FXML
		private ComboBox<String> loadDefects;
		@FXML
		private ComboBox<String> stepsInjectedComboBox;
		@FXML
		private ComboBox<String> stepsRemovedComboBox;
		@FXML
		private ComboBox<String> defectCategoryComboBox;
	
	// labels that can change in runtime
		@FXML
		private Label defectStatus;
		 @FXML
		private Label attributeSaveStatus;
	
	// the two textfields for defect info
		@FXML
		private TextField defectDetails;
		@FXML
		private TextField defectSymptoms;
	
	// All of the buttons
		@FXML
		private Button createDefect;
		@FXML
		private Button updateDefectbtn;
		@FXML
		private Button deleteDefectbtn;
		@FXML
		private Button closeDefect;
		@FXML
		private Button reopenDefect;
		@FXML
		private Button homebtn;
		@FXML
		private Button clearDefect;
		
		
	// DATABASE TOOLS
		private Connection connect;
		private PreparedStatement prepare;
		private ResultSet result;
		
	//-----------------------------------------------------------------------------------
    // Runs at the screen's startup
    //-----------------------------------------------------------------------------------
    @FXML
    void initialize() {
    	// load projects
    	setCBox(selectProject, "projects");
    	// disable all controls except selectProject combobox until a project is selected
    	createDefect.setDisable(true);
		updateDefectbtn.setDisable(true);
		deleteDefectbtn.setDisable(true);
		closeDefect.setDisable(true);
		reopenDefect.setDisable(true);
		clearDefect.setDisable(true);
		
		loadDefects.setDisable(true);
		stepsInjectedComboBox.setDisable(true);
		stepsRemovedComboBox.setDisable(true);
		defectCategoryComboBox.setDisable(true);
		
		defectDetails.setDisable(true);
		defectSymptoms.setDisable(true);
    }
		
	//-----------------------------------------------------------------------------------
    // Action event handlers
    //-----------------------------------------------------------------------------------

	 // Handles when the value in the selectedProject Changes
		@FXML
		void loadProjects(ActionEvent event) {
			if (selectProject.getValue() != null){
				// get that project's cooresponding defects
				defectlistmaker();
				loadDefects.setItems(defectDisplayList);
				
				// enable all controls a project was selected
		    	createDefect.setDisable(false);
				updateDefectbtn.setDisable(false);
				deleteDefectbtn.setDisable(false);
				closeDefect.setDisable(false);
				reopenDefect.setDisable(false);
				clearDefect.setDisable(false);
				
				loadDefects.setDisable(false);
				stepsInjectedComboBox.setDisable(false);
				stepsRemovedComboBox.setDisable(false);
				defectCategoryComboBox.setDisable(false);
				
				defectDetails.setDisable(false);
				defectSymptoms.setDisable(false);
			}	
		}
	
	// Clears the defect
		@FXML
		void clearDefects(ActionEvent event) {
			connect = database.connectDb("empdb");
			try {
		        String defectName = loadDefects.getValue();
		        System.out.println(defectName);
		        String sql = "DELETE FROM defects WHERE defectsName = '" + defectName + "'";
		        connect.createStatement().executeUpdate(sql);
	
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
	
	// handles loading the existing defects into their combo box
		@FXML
		void loadDefectsAction(ActionEvent event) {
			if(loadDefects.getValue() != null) {
				for(int i = 0; i < defectList.size(); i++) {
					if(defectList.get(i).toString().equals(loadDefects.getValue())) {
						System.out.println("Found matching Defect");
						stepsInjectedComboBox.setValue(defectList.get(i).defectsInjected);
						stepsRemovedComboBox.setValue(defectList.get(i).defectsRemoved);
						defectCategoryComboBox.setValue(defectList.get(i).defectsCategory);
						defectDetails.setText(defectList.get(i).defectName);
						defectSymptoms.setText(defectList.get(i).defectDef);
						if(defectList.get(i).status.equals("closed")) {
							System.out.println("closed");
							defectStatus.setText("Status: Closed");
						}
						else if(defectList.get(i).status.equals("open")){
							System.out.println("open");
							defectStatus.setText("Status: Open");
						}
						else {
							System.out.println("Not good");
							defectStatus.setText("Status: Open");
						}
					}
				}
			}
		}
	
	// handles creating new defects and checks if all inputs aren't null
		@FXML
		void createNewDefects(ActionEvent event) {
			boolean hasError = false;
			if (selectProject.getValue() == null){
				 hasError = true;
				 createAlert("Error message", "No Project Selected");
			}
			else if (defectDetails.getText().isBlank()){
				 hasError = true;
				 createAlert("Error message", "defect detail is blank");
			}
			else if (defectSymptoms.getText().isBlank()){
				 hasError = true;
				 createAlert("Error message", "defect symptoms is blank");
			}
			else if (stepsInjectedComboBox.getValue() == null){
				 hasError = true;
				 createAlert("Error message", "Enter the step injected");
			}
			else if (stepsRemovedComboBox.getValue() == null){
				 hasError = true;
				 createAlert("Error message", "Enter the step removed");
			}
			else if (defectCategoryComboBox.getValue() == null){
				 hasError = true;
				 createAlert("Error message", "Enter the defect category");
			}
			
			if(!hasError) {
				// make the new defect
				Defect newDefect = new Defect();
				 newDefect.defectID = 1;
				 newDefect.projectName = selectProject.getValue();
			     newDefect.defectName = defectDetails.getText();
			     newDefect.defectDef = defectSymptoms.getText();
			     newDefect.defectsInjected = stepsInjectedComboBox.getValue();
			     newDefect.defectsRemoved = stepsRemovedComboBox.getValue();
			     newDefect.defectsCategory = defectCategoryComboBox.getValue();
			     newDefect.fix = "null";	// new defects don't have a fix
			     newDefect.status = "open"; // new defects are set to pen

				// insert into database
			    insertNewDefect(newDefect);
			}
		}
		
	// changes a selected defect's status to closed
		@FXML
		void closeDefects(ActionEvent event) {
			if (loadDefects.getValue() != null){
				for(int i = 0; i < defectList.size(); i++) {
					if(defectList.get(i).toString().equals(loadDefects.getValue())) {
						setStatus(defectList.get(i), "closed");
					}
				}
			}
		}
	
	// changes a selected defect's status to open
		@FXML 
		void reopenDefects(ActionEvent event) {
			if (loadDefects.getValue() != null){
				for(int i = 0; i < defectList.size(); i++) {
					if(defectList.get(i).toString().equals(loadDefects.getValue())) {
						setStatus(defectList.get(i), "open");
					}
				}
			}
		}
		
	// loads the life cycle steps into the step injected combo box
		@FXML
		void loadStepsInjected(MouseEvent event) {
			setCBox(stepsInjectedComboBox, "lifecycles");
		}
	
	// loads the life cycle steps into the step removed combo box
		@FXML
		void loadStepsRemoved(MouseEvent event) {
			setCBox(stepsRemovedComboBox, "lifecycles");
		}
		
	// loads the defect choices into defect category combo box
		@FXML
		void loadDefects(MouseEvent event) {
			setCBox(defectCategoryComboBox, "defects");
		}
		
	// loads the defect choices into defect category combo box
		@FXML
		void updateCurrentDefect(ActionEvent event) {
			boolean hasError = false;
			if (selectProject.getValue() == null){
				 hasError = true;
				 createAlert("Error message", "No Project Selected");
			}
			else if (loadDefects.getValue() == null){
				hasError = true;
				createAlert("Error message", "No Defect Selected");
			}
			else if (defectDetails.getText().isBlank()){
				 hasError = true;
				 createAlert("Error message", "defect detail is blank");
			}
			else if (defectSymptoms.getText().isBlank()){
				 hasError = true;
				 createAlert("Error message", "defect symptoms is blank");
			}
			else if (stepsInjectedComboBox.getValue() == null){
				 hasError = true;
				 createAlert("Error message", "Enter the step injected");
			}
			else if (stepsRemovedComboBox.getValue() == null){
				 hasError = true;
				 createAlert("Error message", "Enter the step removed");
			}
			else if (defectCategoryComboBox.getValue() == null){
				 hasError = true;
				 createAlert("Error message", "Enter the defect category");
			}
			
			if(!hasError) {}
			String sql = "UPDATE defects SET defectsDef = ?, defectsInjected = ?, defectsRemoved = ?, defectCategory = ? WHERE defectsName = ?";
			connect = database.connectDb("empdb");
			try {
				prepare = connect.prepareStatement(sql);
				prepare.setString(1, defectSymptoms.getText());
				prepare.setString(2, stepsInjectedComboBox.getValue());
				prepare.setString(3, stepsRemovedComboBox.getValue());
				prepare.setString(4, defectCategoryComboBox.getValue());
				prepare.setString(5, loadDefects.getValue());
				prepare.executeUpdate();	
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
		
	// deletes the currently selected defect from the loadDefect combo box
		@FXML
		void deleteCurrentDefect(ActionEvent event) {
			if (loadDefects.getValue() != null)  {
				for(int i = 0; i < defectList.size(); i++) {
					if(defectList.get(i).toString().equals(loadDefects.getValue())) {
						System.out.println("Found matching Defect");
						deleteDefect(defectList.get(i));
						break;
					}
				}
				createAlert("Error message", "Entry Doesn't Exist");
			}
			else {
				createAlert("Error message", "Select a defect entry");
			}
		}
	
	// Button that goes back to the Effort Console Scrren
		@FXML
		void backHome(ActionEvent event) {
			try {
	    		homebtn.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("EffortConsole.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				
				stage.setScene(scene);
				stage.show();
	    	}catch(Exception e) {e.printStackTrace();}
		}
	
	//-----------------------------------------------------------------------------------
    // function that creates a new alert with a given header and message
    //-----------------------------------------------------------------------------------
		
	void createAlert(String header, String message) {
		Alert alert;
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(header);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
	
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
    // function that populates an array list with values from a SQL table column name
    //-----------------------------------------------------------------------------------
	
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
	
	
	//-----------------------------------------------------------------------------------
    // function that populates an array list will all defects from various array lists
    //-----------------------------------------------------------------------------------
	
	void defectlistmaker() {
		defectList.clear();
		defectDisplayList.clear();

		setArrayList(defectIDs,"defectsID","defects");
		setArrayList(empIDs,"empID","defects");
    	setArrayList(projectNames,"projectName","defects");
    	setArrayList(defectNames,"defectsName","defects");
    	setArrayList(defectDefs,"defectsDef","defects");
    	setArrayList(defectsInjects,"defectsInjected","defects");
    	setArrayList(defectsRemoves,"defectsRemoved","defects");
    	setArrayList(defectsCategories,"defectCategory","defects");
    	setArrayList(fixes,"fix","defects");
    	setArrayList(statuses,"status","defects");
 
		for(int i = 0; i < defectIDs.size(); i++) {
			Defect defect = new Defect();
			defect.defectID = Integer.valueOf(defectIDs.get(i));
			defect.empID = Integer.valueOf(empIDs.get(i));
			defect.projectName = projectNames.get(i);
			defect.defectName = defectNames.get(i);
			defect.defectDef = defectDefs.get(i);
			defect.defectsInjected = defectsInjects.get(i);		
			defect.defectsRemoved = defectsRemoves.get(i);
			defect.defectsCategory = defectsCategories.get(i);
			defect.fix = fixes.get(i);
			defect.status = statuses.get(i);
			defectList.add(defect);
			if(defect.projectName.equals(selectProject.getValue()) && (currentLoginID == defect.empID)) {
				defectDisplayList.add(defect.toString());
			}
		}
	}
		
	//-----------------------------------------------------------------------------------
    // function that takes in an defect and inserts it into the SQL database
    //-----------------------------------------------------------------------------------
	
	void insertNewDefect(Defect newDefect) {
		String sql = "INSERT INTO defects (projectName, defectsName, defectsDef, defectsInjected, defectsRemoved, defectCategory, fix, status, empID)" 
						+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		connect = database.connectDb("empdb");
		try {
			prepare = connect.prepareStatement(sql);
			prepare.setString(1, newDefect.projectName);
			prepare.setString(2, newDefect.defectName);
			prepare.setString(3, newDefect.defectDef);
			prepare.setString(4, newDefect.defectsInjected);
			prepare.setString(5, newDefect.defectsRemoved);
			prepare.setString(6, newDefect.defectsCategory);
			prepare.setString(7, newDefect.fix);
			prepare.setString(8, newDefect.status);
			prepare.setInt(9,  currentLoginID);
			prepare.executeUpdate();
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
    // function that takes in an defect and deletes it from the SQL database
    //-----------------------------------------------------------------------------------
	
	void deleteDefect(Defect defectToDelete) {
		connect = database.connectDb("empdb");
		String query = "DELETE FROM defects WHERE defectsID = ?";
		 PreparedStatement preparedStmt = null;
			try {
				preparedStmt = connect.prepareStatement(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		      try {
				preparedStmt.setInt(1, defectToDelete.defectID);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				preparedStmt.execute();
			}catch(Exception e) {e.printStackTrace();}
	}
	
	//-----------------------------------------------------------------------------------
    // function that takes in an defect name and status and updates the status
    //-----------------------------------------------------------------------------------
	
	
	void setStatus(Defect defectToChange, String newStatus) {
		connect = database.connectDb("empdb");
		String sql = "UPDATE defects SET status = ? WHERE defectsName = ?";
		try {
			prepare = connect.prepareStatement(sql);
			prepare.setString(1, newStatus);
			prepare.setString(2, defectToChange.defectName);
			prepare.executeUpdate();
			
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