package application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;

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
	
	//Getters
	public int get_effortID() {
		return effortID;
	}
	
	public String get_projectName() {
		return projectName;
	}
	
	public String get_lifeCycleStep() {
		return lifeCycleStep;
	}
	
	public String get_category() {
		return category;
	}

	public String get_detail() {
		return detail;
	}
	
	public LocalTime get_start() {
		return start;
	}

	public LocalTime get_end() {
		return end;
	}

	public Duration get_delta() {
		return delta;
	}

	public LocalDate get_date() {
		return date;
	}
	
	//Setters
	public void set_effort_id(int id) {
		this.effortID = id;
	}
	public void set_effort_category(String category) {
		this.category = category;
	}
	public void set_subchoice_category(String detail) {
		this.detail = detail;
	}
	public void set_start(LocalTime startTime) {
		start = startTime;
	}
	public void set_end(LocalTime stopTime) {
		end = stopTime;
	}
	public void set_delta() {
		 delta = Duration.between(start, end);
	}
	public void set_date(LocalDate giveDate) {
		date = giveDate;
	}
}
