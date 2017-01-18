package models;

public enum UserRole {
    USER("User"),
    EDITOR("Editor"),
    UNSPECIFIED("Unspecified"),
    ADMINISTRATOR("Administrator"),
    TEST("Test");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return name();
    }

}
