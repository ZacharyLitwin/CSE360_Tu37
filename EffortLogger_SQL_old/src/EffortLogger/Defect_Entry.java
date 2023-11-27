
//Coded by Alma Babbitt


package EffortLogger;

public class Defect_Entry {
	
	private String name;
	private String detail; 
	private String injected;
	private String removed; 
	private String category; 
	private boolean status;
	private String fix; 
	private static String[] defect_category = {"Not specified", "10 Documentation", "20 Syntax",
												"30 Build, Package", "40 Assignment", "50 Interface",
												"60 Checking", "70 Data", "80 Function", "90 System", 
												"100 Enviroment"
												};
	
	//Constructor
	/*
	 * These attributes describe the structure of the defects
	 * Each attributes creates the information the design needs
	 * These attributes is what makes up the data for a defect entry
	 */
	public Defect_Entry() {
		this.name = null;
		this.detail = null;
		this.injected = null;
		this.removed = null;
		this.category = null;
		this.status = false;
		this.fix = null;
	}
	//Getters
	/*
	 * These methods are what allows the user to access the attributes
	 * Each method is essential to the program to generate a functioning design
	 */
	public String get_name() {
		return name;
	}
	public String get_detail() {
		return detail;
	}
	public String get_injected() {
		return injected;
	}
	public String get_removed() {
		return removed;
	}
	public String get_category() {
		return category;
	}
	public boolean get_status() {
		return status;
	}
	public String get_fix() {
		return fix;
	}
	
	//Setters
	public void set_name(String name) {
		this.name = name;
	}
	public void set_detail(String detail) {
		this.detail = detail;
	}
	public void set_injected(String injected) {
		this.injected = injected;
	}
	public void set_removed(String removed) {
		this.removed = removed;
	}
	public void set_category(int index) {
		this.category = defect_category[index];
	}
	public void set_status(boolean status) {
		this.status = status;
	}
	public void set_fixs(String fixs) {
		this.fix = fixs;
	}
}
