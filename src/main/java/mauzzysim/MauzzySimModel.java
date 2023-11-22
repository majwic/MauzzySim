package mauzzysim;

import java.awt.*;

public class MauzzySimModel {
    private final String PLATFORM_RESTRICTIONS = "Ensure system allows low-level input control";
    private final String SECURITY_PERMISSIONS = "Check and grant necessary permissions for input control";
    private boolean platformRestrictions = false;
    private boolean securityPermissions = false;
    private Robot bot;

    MauzzySimModel() {
        try {
            bot = new Robot();
            bot.setAutoDelay(1);
        } catch (AWTException e) {
            platformRestrictions = true;
        } catch (SecurityException e) {
            securityPermissions = true;
        }
    }

    public String runCommand(String command) {
        if (this.hasLowLevelInputIssue()) {
            return this.lowLevelInputIssue();
        }

        if (command.isEmpty()) {
            return "";
        }

        String[] commandExpanded = command.trim().split("\\s+");
        String commandKeyword = commandExpanded[0];
        int x = 0, y = 0;

        switch(commandKeyword) {
            case "moveTo":
                return Command.moveTo(commandExpanded, bot);
            case "click":
                return Command.click(bot);
        }
        return "";
    }

    private boolean hasLowLevelInputIssue() {
        return this.platformRestrictions || this.securityPermissions;
    }

    private String lowLevelInputIssue() {
        if (this.platformRestrictions) {
            return this.PLATFORM_RESTRICTIONS;
        } else {
            return this.SECURITY_PERMISSIONS;
        }
    }
}
