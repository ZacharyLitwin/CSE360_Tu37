import java.util.*;
import javafx.scene.control.*;

public class Project {
	//Private attributes
	private String name;
	private List<String> life_cycles; //Select which life cycles to choose from all life cycles
	private List<Effort_Entry> efforts; //number/index of entry can be found with this list 
	private List<Defect_Entry> defects;//number/index of entry can be found with this list
	private int number_of_efforts;
	private int number_of_defects;
	
	//Static attributes
	private static int number_of_projects = 0;
	private static String[] all_life_cycles = {"Problem Understanding","Conceptual Design Plan", 
												"Requirements" ,"Conceptual Design" ,"Conceptual Design Review",
												"Detailed Design Plan" ,"Detailed Design/Prototype",
												"Detailed Design Review" ,"Implementation Plan",
												"Test Case Generation" ,"Solution Specification", 
												"Solution Review" ,"Solution Implementation",
												"Unit/System Test" ,"Reflection" ,"Repository Update" ,"Plannning",
												"Information Gathering" ,"Information Understanding" ,"Verifying",
												"Outlining" ,"Drafting" ,"Finalizing" ,"Team Meeting" ,"Coach",
												"Stakeholder Meeting" 
												};
	private static String[] category = {"Plans","Deliverables",
										"Interuptions","Defects","Others"
										};
	private static String[] plans = {"Project Plan", "Risk Management Plan", 
									"Conceptual Design Plan", "Detailed Design Plan",
									"Implementation Plan"
									};
	private static String[] deliverables = {"Conceptual Design", "Detailed Design",
											"Test Cases", "Solution", "Reflection",
											"Outline", "Draft", "Report", "User Defined",
											"Other"
											};
	private static String[] interruptions = {"Break", "Phone", "Teammate","Visitor",
											 "Other"
											};
	
	//Constructors can ONLY be used my managers
	private UserInfo current;
	public Project(String name, UserInfo user) {
		if (user.authorized(Authorization.permissions.WRITE)) {
			this.name = name;
			this.efforts = new ArrayList<Effort_Entry>();
			this.defects = new ArrayList<Defect_Entry>();
			this.number_of_efforts = 0;
			this.number_of_defects = 0;
			number_of_projects++;
			current = user;
		} else {
			System.out.println ("You do not have the necessary permissions to create the project");
		}

	}
	public Project(UserInfo user) {
		if(user.authorized(Authorization.permissions.WRITE)) {
			this.name = String.format("Project%s",number_of_projects);
			this.efforts = new ArrayList<Effort_Entry>();
			this.defects = new ArrayList<Defect_Entry>();
			this.number_of_efforts = 0;
			this.number_of_defects = 0;
			number_of_projects++;
			current = user;
		} else {
			System.out.println ("You do not have the necessary permissions to create the project");
		}
	}
	
	//Getters
	public String get_name() {
		return this.name;
	}
	
	public List<String> get_life_cycle() {
		return life_cycles;
	}
	
	public Effort_Entry get_efforts_entry(int index) {
		return efforts.get(index);
	}
	
	public Defect_Entry get_defects_entry(int index) {
		return defects.get(index);
	}
	
	public int get_number_of_efforts() {
		return number_of_efforts;
	}
	
	public int get_number_of_defects() {
		return number_of_defects;
	}
	
	
	//Setters used ONLY by the manager
	public void set_name(String name) {
		if (current.authorized(Authorization.permissions.UPDATE)) {
			this.name = name;
		} else {
			System.out.println ("You do not have the necessary permissions to perform this action");
		}
	}
	
	public void set_life_cycles(int index) { 
		if (current.authorized(Authorization.permissions.UPDATE)) {
			life_cycles.add(all_life_cycles[index]); //Fill life_cycles with elements from all_life_cycles by calling function in loop
		} else {
			System.out.println ("You do not have the necessary permissions to create the project");
		}
	}
	
	
	//Setters used by the employees
	//Call this function once stop button is pressed
	public void set_efforts_entry(Effort_Entry effort) {
		effort.set_end();
		efforts.add(effort);
	}
	//Call this function once new defect button is pressed
	public void set_defects_entry(Defect_Entry defect) {
		defects.add(defect);
	}
	
	public void set_number_of_efforts() {
		number_of_efforts = efforts.size();
	}
	
	public void set_number_of_defects() {
		number_of_defects = defects.size();
	}
	
	
	
	
	//Call this function once start activity button is pressed
	public Effort_Entry create_effort_entry(int index1, int index2) {
		//new effort entry
		Effort_Entry effort = new Effort_Entry();
		
		//set effort attributes
		effort.set_date();
		effort.set_start();
		effort.set_effort_category(category[index1]); 							//set effort category with value from category array
		if(effort.get_effort_category().equals("Plans")) {
			effort.set_subchoice_category(plans[index2]); 						//set subchoice category with value from plans array	
		} else if(effort.get_effort_category().equals("Deliverables")) {			
			effort.set_subchoice_category(deliverables[index2]); 				//set subchoice category with value from deliverables array			
		} else if(effort.get_effort_category().equals("Interruptions")) {		
			effort.set_subchoice_category(interruptions[index2]); 				//set subchoice category with value from interruptions array		
		} else if(effort.get_effort_category().equals("Defects")) {		
			if(defects.size() > 0) {			
				effort.set_subchoice_category(defects.get(index2).get_name()); //set subchoice category with value from defects arraylist if elements exist			
			}else {		
				effort.set_subchoice_category("- no defect selected -"); 		//else, subchoice category is set to string 
			}	
		} else {
			effort.set_subchoice_category("");									//fill textbox
			//TODO
			//if Other is selected, user should be able to fill text box
		}
		return effort;
	}
		
	//Call this function once new defect button is pressed
	public Defect_Entry create_defect_entry(int category, TextField detail, int fix, int injected, int removed, boolean status, TextField name) {
		//New defect
		Defect_Entry defect = new Defect_Entry();
		
		//Set defect attributes
		//Event handlers will provide parameters
		defect.set_category(category);
		defect.set_detail(detail.getText());
		defect.set_fixs(defects.get(fix).get_name());
		defect.set_injected(life_cycles.get(injected));
		defect.set_removed(life_cycles.get(removed));
		defect.set_status(status);
		defect.set_name(name.getText());
		
		return defect;
		
	}
	
	//TODO
	public void edit_entry() {
		
	}
	public void edit_defect() {
		
	}
	
}