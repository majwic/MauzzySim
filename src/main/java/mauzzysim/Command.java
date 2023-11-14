package mauzzysim;

import java.awt.*;

public class Command {

    public static String moveTo(String[] commandExpanded, Robot bot) {
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

        bot.mouseMove(x, y);

        return "";
    }
}
