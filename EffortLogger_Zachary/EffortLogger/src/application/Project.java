package application;

import java.awt.Button;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Project {
    private Integer id;
    private String name;
    private String updateCycle;
//    private ObservableList<String>
    private ObservableList<String> projectCycles;
    private ComboBox<String> projectCyclesCBox;

	public Project(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.projectCyclesCBox = new ComboBox<String>();
		this.projectCycles = FXCollections.observableArrayList();
	}



	public ComboBox<String> getProjectCyclesCBox() {
		return projectCyclesCBox;
	}

	public void setProjectCyclesCBox(ComboBox<String> projectCyclesCBox) {
		this.projectCyclesCBox = projectCyclesCBox;
	}

	public ObservableList<String> getProjectCycles() {
		return projectCycles;
	}

	public void setProjectCycles(ObservableList<String> projectCycles) {
		this.projectCycles = projectCycles;
	}
	
	public String getUpdateCycle() {
		return updateCycle;
	}

	public void setUpdateCycle(String updateCycle) {
		this.updateCycle = updateCycle;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getName() {
        return name;
    }


}
