package mauzzysim;

public enum CommandUse {
    MOVE_TO("USE: moveTo <int> <int>");

    private final String description;

    CommandUse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
