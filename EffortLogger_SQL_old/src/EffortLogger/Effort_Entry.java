//Coded by Alma Babbitt


package EffortLogger;
import java.time.*;

public class Effort_Entry {
	
	//Private attributes
	private LocalDateTime date; // YYYY/MM/DD
	private LocalDateTime start; // Min/Sec/Ms
	private LocalDateTime end; // Min/Sec/Ms
	private Duration delta; // Min/Sec/Ms
	private String effort_category;
	private String subchoice_category;
	//user id
	
	//Constructor
	/*
	 * These attributes describe the structure of the defects
	 * Each attributes creates the information the design needs
	 * These attributes is what makes up the data for a defect entry
	 */
	public Effort_Entry() {
		this.date = null;
		this.start = null;
		this.end = null;
		this.delta = null;
		this.effort_category = null;
		this.subchoice_category = null;
	}
	
	//Getters
	/*
	 * These methods are what allows the user to access the attributes
	 * Each method is essential to the program to generate a functioning design
	 */
	public String get_effort_category() {
		return effort_category;
	}

	public String get_subchoice_category() {
		return subchoice_category;
	}
	
	public LocalDateTime get_start() {
		return start;
	}

	public LocalDateTime get_end() {
		return end;
	}

	public Duration get_delta() {
		return delta;
	}

	public LocalDateTime get_date() {
		return date;

	}
	
	//Setters
	public void set_effort_category(String effort_category) {
		this.effort_category = effort_category;
	}
	public void set_subchoice_category(String subchoice_category) {
		this.subchoice_category = subchoice_category;
	}
	public void set_start() {
		start = LocalDateTime.now();
	}
	public void set_end() {
		end = LocalDateTime.now();
	}
	public void set_delta() {
		delta = Duration.between(start,end);
	}
	public void set_date() {
		date = LocalDateTime.now();
	}
}
