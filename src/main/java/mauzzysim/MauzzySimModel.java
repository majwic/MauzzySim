package mauzzysim;

import java.awt.*;

public class MauzzySimModel {
    private static final String PLATFORM_RESTRICTIONS = "Ensure system allows low-level input control";
    private static final String SECURITY_PERMISSIONS = "Check and grant necessary permissions for input control";
    private static boolean platformRestrictions = false;
    private static boolean securityPermissions = false;
    private static Robot bot;

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

    public static String runCommand(String command) {
        if (hasLowLevelInputIssue()) {
            return lowLevelInputIssue();
        }

        if (command.isEmpty()) {
            return "";
        }

        String[] commandExpanded = command.trim().split("\\s+");
        String commandKeyword = commandExpanded[0];
        int x = 0, y = 0;

        return switch (commandKeyword) {
            case "moveTo" -> Command.moveTo(commandExpanded, bot);
            case "move" -> Command.move(commandExpanded, bot);
            case "moveToZone" -> Command.moveToZone(commandExpanded, bot);
            case "moveZone" -> Command.moveZone(commandExpanded, bot);
            case "leftClick" -> Command.leftClick(bot);
            case "rightClick" -> Command.rightClick(bot);
            case "wait" -> Command.wait(commandExpanded);
            case "set" -> Command.setVariable(commandExpanded);
            case "capture" -> Command.capture(commandExpanded, bot);
            case "imageCompare" -> Command.imageCompare(commandExpanded);
            default -> "";
        };
    }

    private static boolean hasLowLevelInputIssue() {
        return platformRestrictions || securityPermissions;
    }

    private static String lowLevelInputIssue() {
        if (platformRestrictions) {
            return PLATFORM_RESTRICTIONS;
        } else {
            return SECURITY_PERMISSIONS;
        }
    }
}
