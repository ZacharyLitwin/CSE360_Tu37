//Author: Ishan Yelnoorkar
// Group: TU-37
//Purpose: Creates a effotEntry class that allows the effort information stored in the database to converted to a useful form in java

package application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class effortEntry {
	
	 // Define a date format
    DateTimeFormatter formatterdate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Define a time format (optional)
    DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	//Private attributes
	private int effortID;
	private String projectName;
	private LocalDate date; // YYYY/MM/DD
	private LocalTime start; // Min/Sec/Ms
	private LocalTime end; // Min/Sec/Ms
	private Duration delta; // Min/Sec/Ms
	private String lifeCycleStep;
	private String category;
	private String detail;
	
	//Constructors
	public effortEntry(int id, String projName, String dateString, String startTime, String stopTime, String lifecycle, String cate, String det) {
		effortID = id;
		projectName = projName;
		date = LocalDate.parse(dateString, formatterdate);
		start = LocalTime.parse(startTime, formattertime);
		end = LocalTime.parse(stopTime, formattertime);
		delta = Duration.between(start, end);
		lifeCycleStep = lifecycle;
		category = cate;
		detail = det;
	}

	public int getEffortID() {
		return effortID;
	}

	public void setEffortID(int effortID) {
		this.effortID = effortID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

	public Duration getDelta() {
		return delta;
	}

	public void setDelta(Duration delta) {
		this.delta = delta;
	}

	public String getLifeCycleStep() {
		return lifeCycleStep;
	}

	public void setLifeCycleStep(String lifeCycleStep) {
		this.lifeCycleStep = lifeCycleStep;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
