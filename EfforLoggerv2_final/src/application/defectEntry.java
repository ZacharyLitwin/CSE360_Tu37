//Author: Ishan Yelnoorkar
// Group: TU-37
//Purpose: Creates a defect class that allows the defect information stored in the database to converted to a useful form in java

package application;

public class defectEntry {
	
	private int defectID;
	private String projectName;
	private String name;
	private String detail; 
	private String injected;
	private String removed; 
	private String category; 
	private String status;
	private String fix; 

	//Constructor
	public defectEntry(int defectID, String projectName, String name, String detail, String injected, String removed, String category, String status, String fix) {
		this.defectID = defectID;
		this.projectName = projectName;
		this.name = name;
		this.detail = detail;
		this.injected = injected;
		this.removed = removed;
		this.category = category;
		this.status = status;
		this.fix = fix;
	}

	public int getDefectID() {
		return defectID;
	}
	public void setDefectID(int defectID) {
		this.defectID = defectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getInjected() {
		return injected;
	}
	public void setInjected(String injected) {
		this.injected = injected;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFix() {
		return fix;
	}
	public void setFix(String fix) {
		this.fix = fix;
	}
}
