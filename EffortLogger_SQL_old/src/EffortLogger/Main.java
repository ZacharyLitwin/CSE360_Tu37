package EffortLogger;

import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {
		DateTimeFormatter t1 = DateTimeFormatter.ofPattern("HH:mm:ss a");
		DateTimeFormatter t2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		//Empty object of class Project
		Project effortLogger = new Project("EffortLoggerV1");
		
		System.out.print("Empty Project\n");
		System.out.print("--------------\n");
		System.out.printf("name: %s\n", effortLogger.get_name());
		System.out.printf("# of efforts: %s\n", effortLogger.get_number_of_efforts());
		System.out.printf("# of defects: %s\n", effortLogger.get_number_of_defects());
				
		//Fill the Project object
		for(int i = 0; i < 8; i++) {
			effortLogger.set_life_cycles(i);
		}
		effortLogger.set_name("EffortLoggerV2");
		Effort_Entry effort = effortLogger.create_effort_entry(1, 1);
		//Represents the employees activity time
		for(int i = 0; i < 1; i++) {
			try {
				Thread.sleep(15000);
			}catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		effortLogger.set_efforts_entry(effort);
		
		//Display filled Project object
		System.out.print("\nFilled Project\n");
		System.out.print("--------------\n");
		System.out.printf("name: %s\n", effortLogger.get_name());

		for(int i = 0; i < 8; i++) {
			System.out.printf("life cycle %s: %s\n",i, effortLogger.get_life_cycle(i));
		}
		Effort_Entry data = effortLogger.get_efforts_entry(0);
		System.out.printf("\nstart: %s\n", t1.format(data.get_start()));
		System.out.printf("end: %s\n", t1.format(data.get_end()));
		System.out.printf("delta: %s\n", data.get_delta());
		System.out.printf("date: %s\n", t2.format(data.get_date()));
		System.out.printf("effort category: %s\n", data.get_effort_category());
		System.out.printf("subchoice: %s\n", data.get_subchoice_category());
		System.out.printf("# of efforts: %s\n", effortLogger.get_number_of_efforts());
		System.out.printf("# of defects: %s\n", effortLogger.get_number_of_defects());
	}
}
