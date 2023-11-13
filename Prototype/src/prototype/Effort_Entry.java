/*
 * Authpr: Alma Babbitt
 * */
//Group:	Tu-37

package prototype;
import java.time.LocalDate;
import java.time.Duration;
import java.time.Instant;

public class Effort_Entry {
	
	//Private attributes
	private String name;
	private LocalDate date; // YYYY/MM/DD
	private Instant start; // Min/Sec/Ms
	private Instant end; // Min/Sec/Ms
	private Duration delta; // Min/Sec/Ms
	private String effort_category;
	private String lifeCycle;
	private String subchoice_category;
	
	//Constructors
	public Effort_Entry(String name, LocalDate date, Instant start, Instant end, Duration delta, String lifeCycle, String effort_category, String subchoice_category) {
		this.name = name;
		this.date = date;
		this.start = start;
		this.end = end;
		this.delta = delta;
		this.lifeCycle = lifeCycle;
		this.effort_category = effort_category;
		this.subchoice_category = subchoice_category;
	}

	//Getters
	public String get_effort_category() {
		return effort_category;
	}

	public String get_subchoice_category() {
		return subchoice_category;
	}
	
	public String get_name() {
		return name;
	}
	
	public Instant get_start() {
		return start;
	}

	public Instant get_end() {
		return end;
	}

	public Duration get_delta() {
		return delta;
	}

	public LocalDate get_date() {
		return date;
	}
	
	public String get_lifeCycle() {
		return lifeCycle;
	}
	
	//Setters
	public void set_effort_category(String effort_category) {
		this.effort_category = effort_category;
	}
	public void set_subchoice_category(String subchoice_category) {
		this.subchoice_category = subchoice_category;
	}
	public void set_start() {
		start = Instant.now();
	}
	public void set_end() {
		end = Instant.now();
	}
	public void set_delta() {
		delta = Duration.between(start, end);
	}
	public void set_date() {
		date = LocalDate.now();
	}
	
	public void set_lifeCycle(String lifeCycle) {
		this.lifeCycle = lifeCycle;
	}
}
