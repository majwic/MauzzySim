package mauzzysim;

public enum CommandUse {
    MOVE_TO("USE: moveTo <int> <int>"),
    MOVE("USE: move <int> <int>"),
    MOVE_TO_ZONE("USE: moveToZone <int> <int> <int> <int>"),
    MOVE_ZONE("USE: moveZone <int> <int> <int> <int>"),
    WAIT("USE: wait <+int>");

    private final String description;

    CommandUse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
