package mauzzysim;

public enum CommandUse {
    MOVE_TO("USE: moveTo <int> <int>"),
    MOVE("USE: move <int> <int>"),
    MOVE_TO_ZONE("USE: moveToZone <int> <int> <int> <int>"),
    MOVE_ZONE("USE: moveZone <int> <int> <int> <int>"),
    WAIT("USE: wait <+int>"),
    SET("USE: set <String> <String>"),
    CAPTURE("USE: capture <String> <int> <int> <int> <int>"),
    IMAGE_COMPARE("USE: imageCompare <String> <== || !=> <String> <String-Script>");

    private final String description;

    CommandUse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
