//Group:	Tu-37
// Author: Alma Babbitt

package prototype;
import java.util.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

import javafx.scene.control.*;

public class Project implements Serializable{
	//Private attributes
	private static final long serialVersionUID = -5695355434022433547L;
	private String name;
	private List<String> life_cycles; //Select which life cycles to choose from all life cycles
	private List<Effort_Entry> efforts; //number/index of entry can be found with this list 
	private List<Defect_Entry> defects;//number/index of entry can be found with this list
	private int number_of_efforts;
	private int number_of_defects;
	
	//Constructors can ONLY be used my managers
	private UserInfo current;
	public Project(String name, UserInfo user) {
			this.name = name;
			this.efforts = new ArrayList<Effort_Entry>();
			this.defects = new ArrayList<Defect_Entry>();
			this.number_of_efforts = 0;
			this.number_of_defects = 0;
			current = user;
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
		return efforts.size();
	}
	
	public int get_number_of_defects() {
		return defects.size();
	}
	
	
	//Setters used by the employees
	//Call this function once stop button is pressed
	public void set_efforts_entry(Effort_Entry effort) {
		efforts.add(effort);
	}
	//Call this function once new defect button is pressed
	public void set_defects_entry(Defect_Entry defect) {
		defects.add(defect);
	}
	
	
	
	
	//Call this function once start activity button is pressed
	public Effort_Entry create_effort_entry(String name, LocalDate date, Instant start, Instant end, Duration delta, String lifeCycle, String effort_category, String subchoice_category) {
		Effort_Entry effortEntry = new Effort_Entry(name, date, start, end, delta, lifeCycle, effort_category, subchoice_category);
		return effortEntry;
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