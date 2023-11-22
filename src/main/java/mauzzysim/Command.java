package mauzzysim;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

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

        new BezierMouse(bot).bezierMoveTo(x, y);

        return "";
    }

    public static String click(Robot bot) {
        Random rand = new Random();
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.delay(rand.nextInt(50, 150));
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        return "";
    }
}
