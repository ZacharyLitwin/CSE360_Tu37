//Group:	Tu-37
//Author:	Zachary Litwin


package prototype3;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

// This class allows you to save and load objects to and from files using serialization.
// It provides methods for saving and loading objects, and a main method for demonstrating their usage.
// The saving and loading of files will be used in effort logger 2.0 to save projects, effort logs, defect logs, as well as planning poker estimates.
// An object will first be encrypted before being written to a file and consequently will need to be decrypted before it can be used in the program again
public class ObjectSaver {
	
	
	// The encryption key for securing the objects.
	private static final String ENCRYPTION_KEY = "ThisStringSecure"; // 

		
	// This method takes in an object and a string "fileName"
	// It converts the object to a byte stream then uses a cipher to convert that byte stream to a encrypted byte array
	// That byte array is then written to a file named (fileName) 
	// Lastly it prints a string  to console expressing the success of serialization
	// All file writing is done in a try block as an attempt to handle situations where the writing fails
	 public static void saveObjectToFile(Object object, String fileName) {
	        try {
	        	
	            // Create a Cipher instance for encryption using AES, (Advanced Encryption Standard)
	            Cipher cipher = Cipher.getInstance("AES");
	            // creates a secret encryption key (SecretKey) for use with AES encryption
	            SecretKey secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
	            //This line initializes the cipher object to encryption mode using the AES algorithm and secretKey
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

	            // Serialize the object
	            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	            ObjectOutputStream oos = new ObjectOutputStream(byteStream);
	            oos.writeObject(object);

	            // Encrypt the object into byte array
	            byte[] encryptedData = cipher.doFinal(byteStream.toByteArray());

	            // Write the encrypted data to the file
	            try (FileOutputStream fos = new FileOutputStream(fileName)) {
	                fos.write(encryptedData);
	            }

	            // Close resources
	            oos.close();

	            // Print a success message
	            System.out.println("Object saved to " + fileName);
	        } catch (Exception e) {
	            // Handle any exceptions that may occur during the process
	            e.printStackTrace();
	            System.err.println("Failed to save the object to " + fileName);
	        }
	 }
    
	// This method takes in a string "fileName"
	// It reads the file matching fileName and stores the resulting byte array
	// That byte array is then decrypted and deserialized back into an object which is returned (fileName) 
	// Lastly it prints a string to console expressing the success of deserialization
	// All file reading is done in a try block as an attempt to handle situations where the reading fails
	 public static Object loadObjectFromFile(String fileName) {
	        try {
	            // Create a Cipher instance for decryption using AES, (Advanced Encryption Standard)
	            Cipher cipher = Cipher.getInstance("AES");
	            // Creates a secret encryption key (SecretKey) for use with AES encryption
	            SecretKey secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
	            // This line initializes the cipher object to decryption mode using the AES algorithm and secretKey
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);

	            // create byte array to hold encrypted data
	            byte[] encryptedData;
	            // Read the encrypted data from the file
	            try (FileInputStream fis = new FileInputStream(fileName)) {
	                encryptedData = fis.readAllBytes();
	            }

	            // Decrypt the data into byte array
	            byte[] decryptedData = cipher.doFinal(encryptedData);

	            // Deserialize the object
	            ByteArrayInputStream byteStream = new ByteArrayInputStream(decryptedData);
	            ObjectInputStream ois = new ObjectInputStream(byteStream);
	            Object object = ois.readObject();

	            // Close resources
	            ois.close();

	            // Print a success message
	            System.out.println("Object loaded from " + fileName);

	            // Return the loaded object
	            return object;
	        } catch (Exception e) {
	            // Any exceptions that occurred during the process make it print "failed and return nothing
	            e.printStackTrace();
	            System.err.println("Failed to load the object from " + fileName);
	            return null;
	        }
	    }

	// This main function is purely used to test the OjectSaver methods
    // It creates some example objects using the constructors of the template classes
	// It then saves the objects to files and loads said objects from the files 
    public static void main(String[] args) {
    	
	    // Create two sets of example objects
		Project project = new Project("A Good Project Name", 100);
		Login login = new Login("ZLitwin", "bigstrongpassword");
		PlanningPoker poker = new PlanningPoker(2, "implementation", 20);
		Project project2 = new Project("A Bad Project Name", 9999);
		Login login2 = new Login("ESharp", "biggerstrongerpassword");
		PlanningPoker poker2 = new PlanningPoker(2, "design steps", 20);
		 
		//display the created objects
		System.out.println(""); // just for easier to read spacing
	    System.out.println("The orginal values of the objects: ");
		System.out.println("Orginal Project: " + project.getName() + ", " + project.getData());
		System.out.println("Orginal Login: " + login.getUsername() + ", " + login.getPassword());
		System.out.println("Orginal PlanningPoker: " + poker.getSessionId() + ", " + poker.getItemName() + ", " + poker.getItemEstimate());
		System.out.println("Orginal Project: " + project.getName() + ", " + project.getData());
		System.out.println("Orginal Login: " + login.getUsername() + ", " + login.getPassword());
		System.out.println("Orginal PlanningPoker: " + poker.getSessionId() + ", " + poker.getItemName() + ", " + poker.getItemEstimate());

        // Save all objects to files
    	System.out.println(""); // just for easier to read spacing
        saveObjectToFile(project, "project2.ser");
        saveObjectToFile(login, "login2.ser");
        saveObjectToFile(poker, "poker2.ser");
        saveObjectToFile(project2, "project2.ser");
        saveObjectToFile(login2, "login2.ser");
        saveObjectToFile(poker2, "poker2.ser");

        
        // change the values in the objects
        project.setName("A fantastic Project Name");
        project2.setName("The worst Project Name");
        login.setPassword("A weak Password");
        login2.setUsername("The weakest Password");
        poker.setItemName("process flow");
        poker2.setItemName("testing");
     
         
   	 	// display the changed object information
   	 	System.out.println(""); // just for easier to read spacing
        System.out.println("The changed values of the objects: ");
	   	System.out.println("Changed Project: " + project.getName() + ", " + project.getData());
		System.out.println("Changed Login: " + login.getUsername() + ", " + login.getPassword());
		System.out.println("Changed PlanningPoker: " + poker.getSessionId() + ", " + poker.getItemName() + ", " + poker.getItemEstimate());
		System.out.println("Changed Project: " + project.getName() + ", " + project.getData());
		System.out.println("Changed Login: " + login.getUsername() + ", " + login.getPassword());
		System.out.println("Changed PlanningPoker: " + poker.getSessionId() + ", " + poker.getItemName() + ", " + poker.getItemEstimate());
	 
		
        // save the second set of objects to the files to update values
		System.out.println(""); // just for easier to read spacing
        saveObjectToFile(project2, "project2.ser");
        saveObjectToFile(login2, "login2.ser");
        saveObjectToFile(poker2, "poker2.ser");

        
        // Load objects from files
        Project loadedProject = (Project) loadObjectFromFile("project.ser");
        Login loadedLogin = (Login) loadObjectFromFile("login.ser");
        PlanningPoker loadedPoker = (PlanningPoker) loadObjectFromFile("poker.ser");
        Project loadedProject2 = (Project) loadObjectFromFile("project2.ser");
        Login loadedLogin2 = (Login) loadObjectFromFile("login2.ser");
        PlanningPoker loadedPoker2 = (PlanningPoker) loadObjectFromFile("poker2.ser");
        
        
        // display the saved object information
    	System.out.println(""); // just for easier to read spacing
        System.out.println("The saved values of the objects: ");
        if (loadedProject != null) {
            System.out.println("Saved Project: " + loadedProject.getName() + ", " + loadedProject.getData());
        }
        if (loadedLogin != null) {
            System.out.println("Saved Login: " + loadedLogin.getUsername() + ", " + loadedLogin.getPassword());
        }
        if (loadedPoker != null) {
            System.out.println("Saved PlanningPoker: " + loadedPoker.getSessionId() + ", " + loadedPoker.getItemName() + ", " + loadedPoker.getItemEstimate());
        }
        if (loadedProject2 != null) {
            System.out.println("Saved Project2: " + loadedProject.getName() + ", " + loadedProject.getData());
        }
        if (loadedLogin2 != null) {
            System.out.println("Saved Login2: " + loadedLogin.getUsername() + ", " + loadedLogin.getPassword());
        }
        if (loadedPoker2 != null) {
            System.out.println("Saved PlanningPoker2: " + loadedPoker.getSessionId() + ", " + loadedPoker.getItemName() + ", " + loadedPoker.getItemEstimate());
        }
    }
}
