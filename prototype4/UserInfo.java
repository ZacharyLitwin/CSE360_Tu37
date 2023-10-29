public class UserInfo {
    private String username;
    private String password;
    private Authorization.userRoles roles;
    private Authorization.permissions[] permissions;

    public UserInfo(String username, String password, Authorization.userRoles roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = roles.getAllowedPermissions();
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Authorization.userRoles getRoles() {
        return roles;
    }

    public Authorization.permissions[] getAllowedPermissions() {
        return permissions;
    }

    public boolean authorized(Authorization.permissions permission) {
        for (Authorization.permissions current : permissions) {
             if(current == permission) {
                  return true;
             }
        }
        return false;
      }
}
