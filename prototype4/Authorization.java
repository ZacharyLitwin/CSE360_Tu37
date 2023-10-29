public class Authorization {
    enum permissions { UPDATE, WRITE, READ, DELETE }

    enum userRoles {
        MANAGER(new permissions[] {permissions.UPDATE, permissions.WRITE, permissions.READ, permissions.DELETE}),
        EMPLOYEE(new permissions[] {permissions.WRITE, permissions.READ});

        private final permissions[] allowedPermissions;

        userRoles(permissions[] allowedPermissions) {
            this.allowedPermissions = allowedPermissions;
        }

        public permissions[] getAllowedPermissions() {
            return allowedPermissions;
        }
    }
}
