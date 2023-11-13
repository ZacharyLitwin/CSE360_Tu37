/*
 * Author      : Ishan Yelnoorkar
 * Date        : 10/29/2023
 * Description : Creates a user with their respective authorization roles and permissions
 */
//Group:	Tu-37

package prototype;
import java.io.Serializable;

public class UserInfo implements Serializable{
	// Initialize variables
	private static final long serialVersionUID = 5541279089126047339L;
    private String username;
    private String password;
    private Authorization.userRoles roles;
    private Authorization.permissions[] permissions;

    /*
     * Constructor for UserInfo
     * 
     * @param username- The user name of the user
     * @param password- The password of the user
     * @param roles   - The authorization roles for the user
     */
    public UserInfo(String username, String password, Authorization.userRoles roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = roles.getAllowedPermissions();
    }
    
    /*
     * Get the user name of the user
     * @return the user name
     */
    public String getUserName() {
        return username;
    }

    /*
     * Get the password of the user
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /*
     * Get the role of the user
     * @return the user's role
     */
    public Authorization.userRoles getRoles() {
        return roles;
    }
    
    /*
     * Get the allowed permissions for the user
     * @return the array of allowed permissions
     */
    public Authorization.permissions[] getAllowedPermissions() {
        return permissions;
    }
    
    /*
     * Check if the user is authorized for a specific permission
     * @param permission- The permission to be checked
     * @return true if the user is authorized, otherwise false
     */
    public boolean authorized(Authorization.permissions permission) {
        for (Authorization.permissions current : permissions) {
             if(current == permission) {
                  return true;
             }
        }
        return false;
      }
}
