package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProjectCyclesController {

    @FXML
    private TableColumn<Project, String> projectCycles;

    @FXML
    private TableView<Project> table;
    
    private ObservableList<String> cycles = FXCollections.observableArrayList();

}
