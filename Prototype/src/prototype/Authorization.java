/*
 * Author      : Ishan Yelnoorkar
 * Date        : 10/29/2023
 * Description : Defines permissions and user roles for authorization
 */
//Group:	Tu-37
package prototype;
public class Authorization {
	// Enum to represent various permissions available for users
    enum permissions { UPDATE, WRITE, READ, DELETE }

    // Enum to define user roles with their allowed permissions
    enum userRoles {
        MANAGER(new permissions[] {permissions.UPDATE, permissions.WRITE, permissions.READ, permissions.DELETE}),
        EMPLOYEE(new permissions[] {permissions.WRITE, permissions.READ}),
        THIRDPARTY(new permissions[] {permissions.READ});
        private final permissions[] allowedPermissions;

        // Constructor for userRoles enum
        userRoles(permissions[] allowedPermissions) {
            this.allowedPermissions = allowedPermissions;
        }

        /*
         * Get the allowed permissions for this user role
         * @return an array of allowed permissions
         */
        public permissions[] getAllowedPermissions() {
            return allowedPermissions;
        }
    }
}
