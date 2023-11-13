//Group:	Tu-37
//Author:	Zachary Litwin


package prototype;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

// This class allows you to save and load objects to and from files using serialization.
// It provides methods for saving and loading objects, and a main method for demonstrating their usage.
// The saving and loading of files will be used in effort logger 2.0 to save projects, effort logs, defect logs, as well as planning poker estimates.
// An object will first be encrypted before being written to a file and consequently will need to be decrypted before it can be used in the program again
public class ObjectSaver {
	
	
	// The encryption key for securing the objects.
	private static final String ENCRYPTION_KEY = "ThisStringSecure";  
	private static final long serialVersionUID = 5541279089126047339L;
		
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
}
