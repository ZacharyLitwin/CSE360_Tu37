package prototype;
import java.time.LocalDate;
import java.time.Duration;
import java.time.Instant;

public class Effort_Entry {
	
	//Private attributes
	private LocalDate date; // YYYY/MM/DD
	private Instant start; // Min/Sec/Ms
	private Instant end; // Min/Sec/Ms
	private Duration delta; // Min/Sec/Ms
	private String effort_category;
	private String subchoice_category;
	
	//Constructors
	public Effort_Entry() {
		this.date = null;
		this.start = null;
		this.end = null;
		this.delta = null;
		this.effort_category = null;
		this.subchoice_category = null;
	}
	
	//Getters
	public String get_effort_category() {
		return effort_category;
	}

	public String get_subchoice_category() {
		return subchoice_category;
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
}
