package application;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class defectEditorController {
	@FXML
	private ComboBox<String> selectProject;
	@FXML
	private Button clearDefect;
	@FXML
	private ComboBox<String> loadDefects;
	@FXML
	private Button createDefect;
	@FXML
	private Label defectStatus;
	@FXML
	private TextField defectDetails;
	@FXML
	private Button closeDefect;
	@FXML
	private Button reopenDefect;
	@FXML
	private TextField defectSymptoms;
	@FXML
	private ComboBox<String> stepsInjectedComboBox;
	@FXML
	private ComboBox<String> stepsRemovedComboBox;
	@FXML
	private ComboBox<String> defectCategoryComboBox;
	@FXML
	private Button updateDefectbtn;
	@FXML
	private Button deleteDefectbtn;
	@FXML
	private Button homebtn;
	
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	
	@FXML
	void loadProjects(MouseEvent event) {
		String sql = "SELECT projectName FROM projects";
		connect = database.connectDb("definitions");
    	try {
    		ObservableList<String> array = FXCollections.observableArrayList();
			result = connect.createStatement().executeQuery(sql);
    		while(result.next()) {
    			array.add(new String(result.getString(1)));
			}
    		selectProject.setItems(array);
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
	
	@FXML
	void clearDefects(ActionEvent event) {
		connect = database.connectDb("definitions");
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
	
	@FXML
	void loadDefectsAction(MouseEvent event) {
		String sql = "SELECT defectsName FROM defects";
		connect = database.connectDb("definitions");
    	try {
    		ObservableList<String> array = FXCollections.observableArrayList();
			result = connect.createStatement().executeQuery(sql);
    		while(result.next()) {
    			array.add(new String(result.getString(1)));
			}
    		loadDefects.setItems(array);
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
	
	@FXML
	void createNewDefects(ActionEvent event) {
		String sql = "INSERT INTO defects (defectsID, projectName, defectsName, defectsDef, defectsInjected, defectsRemoved, defectCategory, fix, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		connect = database.connectDb("definitions");
		try {
			if (loadDefects.getItems().isEmpty() || loadDefects.getValue() == null) {
				if (selectProject.getValue().equals(null) || defectDetails.getText().isBlank() || defectSymptoms.getText().isBlank() || stepsInjectedComboBox.getValue().equals(null) || stepsRemovedComboBox.getValue().equals(null) || defectCategoryComboBox.getValue().equals(null)) {
					createAlert("Error message", "Enter all blank fields");
				} else {
					createAlert("Success", "Successful Defect Entry creation");
					
					prepare = connect.prepareStatement(sql);
					prepare.setInt(1, 1);
					prepare.setString(2, selectProject.getValue());
					prepare.setString(3, defectDetails.getText());
					prepare.setString(4, defectSymptoms.getText());
					prepare.setString(5, stepsInjectedComboBox.getValue());
					prepare.setString(6, stepsRemovedComboBox.getValue());
					prepare.setString(7, defectCategoryComboBox.getValue());
					prepare.setString(8, "null");
					if (defectStatus.getText().contains("Closed")) {
						prepare.setString(9, "open");
						defectStatus.setText("Status: Open");
					} 
					prepare.executeUpdate();
				}
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
	
	@FXML
	void closeDefects(ActionEvent event) {
		String sql = "UPDATE defects SET status = 'closed' WHERE defectsName = ?";
		connect = database.connectDb("definitions");
		try {
			prepare = connect.prepareStatement(sql);
			if (!loadDefects.getItems().isEmpty() || loadDefects.getValue() != null)  {
				defectStatus.setText("Status: Closed");
				prepare.setString(1, loadDefects.getValue());
				prepare.executeUpdate();
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
	
	@FXML 
	void reopenDefects(ActionEvent event) {
		String sql = "UPDATE defects SET status = 'open' WHERE defectsName = ?";
		connect = database.connectDb("definitions");
		try {
			prepare = connect.prepareStatement(sql);
			if (!loadDefects.getItems().isEmpty() || loadDefects.getValue() != null)  {
				defectStatus.setText("Status: Open");
				prepare.setString(1, loadDefects.getValue());
				prepare.executeUpdate();
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
	
	@FXML
	void loadStepsInjected(MouseEvent event) {
		String sql = "SELECT name FROM lifecycles";
		connect = database.connectDb("definitions");
    	try {
    		ObservableList<String> array = FXCollections.observableArrayList();
			result = connect.createStatement().executeQuery(sql);
			System.out.println(result);
    		while(result.next()) {
    			array.add(new String(result.getString(1)));
			}
    		stepsInjectedComboBox.setItems(array);
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
	
	
	@FXML
	void loadStepsRemoved(MouseEvent event) {
		String sql = "SELECT name FROM lifecycles";
		connect = database.connectDb("definitions");
    	try {
    		ObservableList<String> array = FXCollections.observableArrayList();
			result = connect.createStatement().executeQuery(sql);
			System.out.println(result);
    		while(result.next()) {
    			array.add(new String(result.getString(1)));
			}
    		stepsRemovedComboBox.setItems(array);
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
	
	@FXML
	void loadDefects(MouseEvent event) {
    	ObservableList<String> array = FXCollections.observableArrayList("Not specified", "10 Documentation", "20 Syntax", "30 Build, Package", "40 Assignment", "50 Interface", "60 Checking", "70 Data", "80 Function", "90 System", "100 Environment");
    	defectCategoryComboBox.setItems(array);
	}
	
	@FXML
	void updateCurrentDefect(ActionEvent event) {
		String sql = "UPDATE defects SET defectsDef = ?, defectsInjected = ?, defectsRemoved = ?, defectCategory = ? WHERE defectsName = ?";
		connect = database.connectDb("definitions");
		try {
			prepare = connect.prepareStatement(sql);
			if (!loadDefects.getItems().isEmpty() || loadDefects.getValue() != null)  {
				if (selectProject.getValue().equals(null) || defectDetails.getText().isBlank() || defectSymptoms.getText().isBlank() || stepsInjectedComboBox.getValue().equals(null) || stepsRemovedComboBox.getValue().equals(null) || defectCategoryComboBox.getValue().equals(null)) {
					createAlert("Error message", "Enter all blank fields");
				} else {
					createAlert("Success", "Successfully Updated Defect Entry");
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, defectSymptoms.getText());
					prepare.setString(2, stepsInjectedComboBox.getValue());
					prepare.setString(3, stepsRemovedComboBox.getValue());
					prepare.setString(4, defectCategoryComboBox.getValue());
					prepare.setString(5, loadDefects.getValue());
					prepare.executeUpdate();
				}
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
	
	@FXML
	void deleteCurrentDefect(ActionEvent event) {
		String sql = "DELETE FROM defects WHERE defectsName = ?";
		connect = database.connectDb("definitions");
		try {
			prepare = connect.prepareStatement(sql);
			if (loadDefects.getItems().isEmpty() || loadDefects.getValue() == null)  {
				createAlert("Error message", "Select a defect entry");
			} else {
					createAlert("Success", "Successfully Updated Defect Entry");
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, loadDefects.getValue());
					prepare.executeUpdate();
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
	
	void createAlert(String header, String message) {
		Alert alert;
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(header);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
}