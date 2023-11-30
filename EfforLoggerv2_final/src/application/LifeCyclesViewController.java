//Author: Alma Babbit
// Group: TU-37
//Purpose: Controls the view life cycles screen. Only displays a project's life cycles

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class LifeCyclesViewController {

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> categoryClmn;

    @FXML
    private TableColumn<?, ?> cycleIDClmn;

    @FXML
    private TableColumn<?, ?> cycleNameClmn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<?, ?> deliverablesClmn;

    @FXML
    private Button editBtn;

    @FXML
    private Button effortConsoleBtn;

    @FXML
    private ComboBox<?> lifeCycleCBox;

    @FXML
    private ComboBox<?> lifeCycleCBox1;

    @FXML
    private Button logOut;

    @FXML
    private Button projectBtn;

    @FXML
    private TextField projectID;

    @FXML
    private TextField projectName;

    @FXML
    private TableView<?> projectTbl;

    @FXML
    private ScrollBar scroll;

    @FXML
    private TextField search;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void effortConsole(ActionEvent event) {

    }

    @FXML
    void logOutBtn(ActionEvent event) {

    }

    @FXML
    void project(ActionEvent event) {

    }

    @FXML
    void searchTable(ActionEvent event) {

    }

}
