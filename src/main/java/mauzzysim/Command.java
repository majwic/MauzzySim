package mauzzysim;

import java.awt.*;

public class Command {

    public static String moveTo(String[] commandExpanded) {
        if (commandExpanded.length != 3) {
            return CommandUse.MOVE_TO.getDescription();
        }

        int x, y;
        try {
            x = Integer.parseInt(commandExpanded[1]);
            y = Integer.parseInt(commandExpanded[2]);
        } catch (NumberFormatException e) {
            return CommandUse.MOVE_TO.getDescription();
        }

        try {
            Robot bot = new Robot();
            bot.mouseMove(x, y);
        } catch (AWTException e) {
            return CommandUse.PLATFORM_RESTRICTIONS.getDescription();
        } catch (SecurityException e) {
            return CommandUse.SECURITY_PERMISSIONS.getDescription();
        }

        return "";
    }
}
