package mauzzysim;

public enum CommandUse {
    PLATFORM_RESTRICTIONS("Ensure system allows low-level input control"),
    SECURITY_PERMISSIONS("Check and grant necessary permissions for input control"),
    MOVE_TO("USE: moveTo <int> <int>");

    private final String description;

    CommandUse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
