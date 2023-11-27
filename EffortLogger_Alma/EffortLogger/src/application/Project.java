package application;

import java.awt.Button;

public class Project {
    private Integer id;
    private String name;
    private String updateCycle;
    private String[] projectCycles;

	public Project(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String[] getProjectCycles() {
		return projectCycles;
	}

	public void setProjectCycles(String[] projectCycles) {
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
