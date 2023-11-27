package application;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProjectViewController implements Initializable {

    @FXML
    private TableColumn<Project, Integer> id;

    @FXML
    private TableColumn<Project, String> name;
    
    @FXML 
    private TableColumn<Project, String> lifeCycles;

    @FXML 
    private TableColumn<Project, String> updateCycle;
    
    @FXML 
    private TableColumn<Project, String> addDelete;
    
    @FXML
    private TableView<Project> table;

    @FXML
    private TextField txtName;
    
    @FXML
    private Button add;
    
    @FXML
    private Button delete;
    
    
	//	DATABASE TOOLS
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

	private ObservableList<Project> projects = FXCollections.observableArrayList();
	private String[] allLifeCycles = null;

	private Project project = null;
	
	private boolean goAdd = false;
	
	private boolean goDelete = false;
	
	private String projectName = "";
	
	private String lifeCycle = "";
	
	public static String[] cycles = null;
	
    @FXML
    private void getRow(MouseEvent e) {
    	//When mouse clicked on a row in name column
    	name.setCellFactory(TextFieldTableCell.<Project>forTableColumn());
        name.setOnEditCommit(event ->{
            project = event.getTableView().getItems().get(event.getTablePosition().getRow());
            project.setName(event.getNewValue());
            try {
            	connect = database.connectDb("definitions");
            	String sql = "UPDATE projects SET name = ? where projectId = ?";
            	prepare = connect.prepareStatement(sql);
        		prepare.setString(1, project.getName());
        		prepare.setInt(2, project.getId());
        		prepare.executeUpdate();
            }catch(Exception ev) {ev.printStackTrace();} 
        }); 
        
        //When mouse clicked on a row in a update cycle column
        updateCycle.setCellFactory(ComboBoxTableCell.<Project,String>forTableColumn(allLifeCycles));
        updateCycle.setOnEditCommit(event ->{
            project = event.getTableView().getItems().get(event.getTablePosition().getRow());
            project.setUpdateCycle(event.getNewValue());
            projectName = project.getName();
            lifeCycle = project.getUpdateCycle();
            goAdd = true;
            
            
        });       
    }

    @FXML
    private void insertData(ActionEvent event){

        if(!txtName.getText().isEmpty()){
        	Project newData = null;
        	connect = database.connectDb("definitions");
        	String sql = "INSERT INTO projects (name) VALUES (?)";
        	String sql2 = "SELECT * FROM projects WHERE projectID = (SELECT max(projectID) FROM projects)";
        	
        	try {
        		prepare = connect.prepareStatement(sql);
        		prepare.setString(1, txtName.getText());
        		prepare.executeUpdate();
        		
        		prepare = connect.prepareStatement(sql2);
        		result = prepare.executeQuery();
        		while(result.next()) {
        			newData = new Project(result.getInt(1),txtName.getText());
            	}          		
        	}catch(Exception e) {e.getStackTrace();}
        	table.getItems().add(newData);
            txtName.clear();
        }else{
            System.out.println("Fields should not be empty.");
        }
        
    }
        
    @FXML
    private void deleteData(ActionEvent event){
        TableView.TableViewSelectionModel<Project> selectionModel = table.getSelectionModel();
        if(selectionModel.isEmpty()){
            System.out.println("You need select a data before deleting.");
        }

        ObservableList<Integer> list = selectionModel.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);

        Arrays.sort(selectedIndices);

        for(int i = selectedIndices.length - 1; i >= 0; i--){
            selectionModel.clearSelection(selectedIndices[i].intValue());
            table.getItems().remove(selectedIndices[i].intValue());
        }
    }

    private void getData() {
    	connect = database.connectDb("definitions");
    	String sql = "SELECT * FROM projects";
    	
    	try {
        	prepare = connect.prepareStatement(sql);
        	result = prepare.executeQuery();
        	while(result.next()) {
				projects.add(new Project(result.getInt(1), result.getString(2)));
        	}
	   	}catch(Exception e) {e.printStackTrace();}    	
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
	  
	private void setEditCol() {
        Callback<TableColumn<Project, String>, TableCell<Project, String>> cellFactory = (TableColumn<Project, String> param) -> {
            // make cell containing buttons
            final TableCell<Project, String> cell = new TableCell<Project, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button deleteBtn = new Button("Delete");
                        Button addBtn = new Button("Add");
                     
                        deleteBtn.setOnMouseClicked((MouseEvent event) -> {
             
//                            try {
//                                project = projectsTable.getSelectionModel().getSelectedItem();
//                                query = "DELETE FROM `project` WHERE id  ="+project.getId();
//                                connection = DbConnect.getConnect();
//                                preparedStatement = connection.prepareStatement(query);
//                                preparedStatement.execute();
//                                refreshTable();
//                                
//                            } catch (SQLException ex) {
//                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                            
                           

                          

                        });
                        addBtn.setOnMouseClicked((MouseEvent event) -> {
                        	connect = database.connectDb("definitions");
                        	String sql = "INSERT INTO entries (projectName, lifeCycle) VALUES (?,?)";  
                        	if(goAdd) {
                	            try {	        	
                	            	prepare = connect.prepareStatement(sql);
                	            	prepare.setString(1, projectName);
                	            	prepare.setString(2, lifeCycle);
                	            	prepare.executeUpdate();
                	            	
                	            	String sql2 = "SELECT * FROM entries WHERE projectName = ?";
                            		List<String> list = new ArrayList<String>();
                            		prepare = connect.prepareStatement(sql2);
                            		prepare.setString(1, projectName);
                            		result = prepare.executeQuery();
                            		while(result.next()) {
                            			list.add(new String(result.getString(3)));
                        			}
                            		project.setProjectCycles(new String[list.size()]) ;
                        			for (int i = 0; i < list.size(); i++) {
                        		        project.getProjectCycles()[i] = list.get(i);
                        		        System.out.println(project.getProjectCycles()[i]);
                        			}
                	            }catch(Exception ev) {ev.printStackTrace();}
                        	} 
                        	goAdd = false;
                      
                            

                           

                        });

                        HBox managebtn = new HBox(addBtn, deleteBtn);
                        managebtn.setStyle("-fx-alignment:center");
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         addDelete.setCellFactory(cellFactory);
	}
	private String[] getProjectCyclesFromRow() {
		table.getSelectionModel().setCellSelectionEnabled(true);
		ObservableList<Project> selectedCells = table.getSelectionModel().getSelectedItems();
		return selectedCells.get(0).getProjectCycles();

	}
	private void setProjectCycles() {
		
		Callback<TableColumn<Project, String>, TableCell<Project, String>> cellFactory = (TableColumn<Project, String> param) -> {
	        // make cell containing buttons
	        final TableCell<Project, String> cell = new TableCell<Project, String>() {
	            @Override
	            public void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);
	                //that cell created only on non-empty rows
	                if (empty) {
	                    setGraphic(null);
	                    setText(null);
	                } else {
//	                	cycles = getProjectCyclesFromRow();
	                    Button addBtn = new Button("view project cycles");
	                    addBtn.setOnMouseClicked((MouseEvent event) -> {
	                    	try {
	                			Parent root = FXMLLoader.load(getClass().getResource("ProjectCycles.fxml"));
	                			Stage stage = new Stage();
	                			Scene scene = new Scene(root);
	                			
	                			stage.setScene(scene);
	                			stage.show();
	                    	}catch(Exception e) {e.printStackTrace();}
	                        
	
	                       
	
	                    });
	                    HBox managebtn = new HBox(addBtn);
	                    managebtn.setStyle("-fx-alignment:center");
	                    setGraphic(managebtn);
	                    setText(null);
	                }
	            }
            };

            return cell;
        };
         lifeCycles.setCellFactory(cellFactory);
	}
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	setAllLifeCycles();
    	
        id.setCellValueFactory(new PropertyValueFactory<Project, Integer>("id"));
        
        name.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        
        updateCycle.setCellValueFactory(new PropertyValueFactory<Project, String>("updateCycle"));
        updateCycle.setCellFactory(ComboBoxTableCell.<Project, String>forTableColumn());

        setProjectCycles();
        
        setEditCol();
                           
        getData();
        
        table.setItems(projects);

    }
}