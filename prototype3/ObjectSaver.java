package prototype3;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

//This class allows you to save and load objects to and from files using serialization.
// It provides methods for saving and loading objects, and a main method for demonstrating their usage.
public class ObjectSaver {
	
	
	// The encryption key for securing the objects.
	private static final String ENCRYPTION_KEY = "ThisStringSecure"; // 

		
	// This method saves an object to a file using object serialization.
    // It takes an object and a file name as parameters.
	// prints a string  to console expressing the success of serialization
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
    
    // This method loads an object from a file using by Deserializing it.
    // It takes the file name as a parameter.
    // returns an object
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

    // Create some example objects using the constructors
    public static void main(String[] args) {
    	
        // Create some example objects
    	 Project project = new Project("Can I Find This", 100);
    	 Login login = new Login("zlitwin", "bigstrongpassword");
    	 PlanningPoker poker = new PlanningPoker(2, "implementation", 20);

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
