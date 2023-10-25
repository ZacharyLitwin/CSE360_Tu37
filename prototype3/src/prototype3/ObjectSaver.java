package prototype3;
import java.io.*;

//This class allows you to save and load objects to and from files using serialization.
// It provides methods for saving and loading objects, and a main method for demonstrating their usage.
public class ObjectSaver {
	
	// This method saves an object to a file using object serialization.
    // It takes an object and a file name as parameters.
	// returns a string expressing the success of serialization
    public static void saveObjectToFile(Object object, String fileName) {
    	// Create an ObjectOutputStream to write objects to the specified file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
        	// Serialize and write the object to the file
            oos.writeObject(object);
            // Close the ObjectOutputStream to release resources
            oos.close();
            // Print a success message
            System.out.println("Object saved to " + fileName);
        } catch (IOException e) {
        // Handle any IOException that may occur during the process
            e.printStackTrace();
            System.err.println("Failed to save the object to " + fileName);
        }
    }
    
    // This method loads an object from a file using by Deserializing it.
    // It takes the file name as a parameter.
    // returns an object
    public static Object loadObjectFromFile(String fileName) {
    	// Create an ObjectInputStream to read objects from the specified file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)) ) {
        	// Deserialize and read the object from the file
            Object object = ois.readObject();
            // Close the ObjectInputStream to release resources
            ois.close();
            // Print a success message
            System.out.println("Object loaded from " + fileName);
            // Return the loaded object
            return object;
        } catch (IOException | ClassNotFoundException e) {
        // Handle any IOException or ClassNotFoundException that may occur during the process
            e.printStackTrace();
            System.err.println("Failed to load the object from " + fileName);
            return null;
        }
    }

    // Create some example objects using the constructors
    public static void main(String[] args) {
    	
        // Create some example objects
    	 Project project = new Project("Sample Project", 100);
    	 Login login = new Login("myusername", "mypassword");
    	 PlanningPoker poker = new PlanningPoker(1, "Sample Item", 10);

        // Save objects to files
        saveObjectToFile(project, "project.ser");
        saveObjectToFile(login, "login.ser");
        saveObjectToFile(poker, "poker.ser");

        // Load objects from files
        Project loadedProject = (Project) loadObjectFromFile("project.ser");
        Login loadedLogin = (Login) loadObjectFromFile("login.ser");
        PlanningPoker loadedPoker = (PlanningPoker) loadObjectFromFile("poker.ser");

        // Do something with the loaded objects
        if (loadedProject != null) {
            System.out.println("Loaded Project: " + loadedProject.getName() + ", " + loadedProject.getData());
        }
        if (loadedLogin != null) {
            System.out.println("Loaded Login: " + loadedLogin.getUsername() + ", " + loadedLogin.getPassword());
        }
        if (loadedPoker != null) {
            System.out.println("Loaded PlanningPoker: " + loadedPoker.getSessionId() + ", " + loadedPoker.getItemName() + ", " + loadedPoker.getItemEstimate());
        }
    }
}
