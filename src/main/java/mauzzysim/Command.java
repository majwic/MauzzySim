package mauzzysim;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.Random;

public class Command {

    public static String moveTo(String[] commandExpanded, Robot bot) {
        if (commandExpanded.length != 3) {
            return CommandUse.MOVE_TO.getDescription();
        }
        replaceVariables(commandExpanded, 1);

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

    public static String move(String[] commandExpanded, Robot bot) {
        if (commandExpanded.length != 3) {
            return CommandUse.MOVE.getDescription();
        }
        replaceVariables(commandExpanded, 1);

        int x, y;
        try {
            x = Integer.parseInt(commandExpanded[1]);
            y = Integer.parseInt(commandExpanded[2]);
        } catch (NumberFormatException e) {
            return CommandUse.MOVE.getDescription();
        }

        new BezierMouse(bot).bezierMove(x, y);

        return "";
    }

    public static String moveToZone(String[] commandExpanded, Robot bot) {
        if (commandExpanded.length != 5) {
            return CommandUse.MOVE_TO_ZONE.getDescription();
        }
        replaceVariables(commandExpanded, 1);

        int x1, y1, x2, y2;
        try {
            x1 = Integer.parseInt(commandExpanded[1]);
            y1 = Integer.parseInt(commandExpanded[2]);
            x2 = Integer.parseInt(commandExpanded[3]);
            y2 = Integer.parseInt(commandExpanded[4]);
        } catch (NumberFormatException e) {
            return  CommandUse.MOVE_TO_ZONE.getDescription();
        }

        new BezierMouse(bot).bezierMoveToZone(x1, y1, x2, y2);

        return "";
    }

    public static String moveZone(String[] commandExpanded, Robot bot) {
        if (commandExpanded.length != 5) {
            return CommandUse.MOVE_ZONE.getDescription();
        }
        replaceVariables(commandExpanded, 1);

        int x1, y1, x2, y2;
        try {
            x1 = Integer.parseInt(commandExpanded[1]);
            y1 = Integer.parseInt(commandExpanded[2]);
            x2 = Integer.parseInt(commandExpanded[3]);
            y2 = Integer.parseInt(commandExpanded[4]);
        } catch (NumberFormatException e) {
            return  CommandUse.MOVE_TO_ZONE.getDescription();
        }

        new BezierMouse(bot).bezierMoveZone(x1, y1, x2, y2);

        return "";
    }

    public static String leftClick(Robot bot) {
        Random rand = new Random();
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.delay(rand.nextInt(50, 150));
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        return "";
    }

    public static String rightClick(Robot bot) {
        Random rand = new Random();
        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        bot.delay(rand.nextInt(50, 150));
        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

        return "";
    }

    public static String wait(String[] commandExpanded) {
        if (commandExpanded.length != 2) {
            return CommandUse.WAIT.getDescription();
        }
        replaceVariables(commandExpanded, 1);

        int milliseconds;
        try {
            milliseconds = Integer.parseInt(commandExpanded[1]);
            Thread.sleep(milliseconds);
        } catch (NumberFormatException | InterruptedException e) {
            return CommandUse.WAIT.getDescription();
        }

        return "";
    }

    public static String setVariable(String[] commandExpanded) {
        if (commandExpanded.length != 3) {
            return CommandUse.SET.getDescription();
        }
        replaceVariables(commandExpanded, 2);

        EnvVariables.setVariable(commandExpanded[1], commandExpanded[2]);

        return "";
    }

    private static void replaceVariables(String[] commandExpanded, int start) {
        for (int i = start; i < commandExpanded.length; i++) {
            String currentToken = commandExpanded[i];

            if (EnvVariables.containsVariable(currentToken, String.class)) {
                String variableValue = (String) EnvVariables.getVariable(currentToken);
                commandExpanded[i] = variableValue;
            }
        }
    }

    public static String capture(String [] commandExpanded, Robot bot) {
        if (commandExpanded.length != 6) {
            return CommandUse.CAPTURE.getDescription();
        }

        int x1, y1, x2, y2;
        try {
            x1 = Integer.parseInt(commandExpanded[2]);
            y1 = Integer.parseInt(commandExpanded[3]);
            x2 = Integer.parseInt(commandExpanded[4]);
            y2 = Integer.parseInt(commandExpanded[5]);
        } catch (NumberFormatException e) {
            return CommandUse.CAPTURE.getDescription();
        }

        BufferedImage capturedImage = ImageUtils.captureScreenRegion(x1, y1, x2, y2, bot);
        EnvVariables.setVariable(commandExpanded[1], capturedImage);

        return "";
    }

    public static String imageCompare(String[] commandExpanded) {
        if (commandExpanded.length != 5) {
            return CommandUse.IMAGE_COMPARE.getDescription();
        }

        if (!EnvVariables.containsVariable(commandExpanded[1], BufferedImage.class)
                && !EnvVariables.containsVariable(commandExpanded[3], BufferedImage.class)
                && !(commandExpanded[2].equals("==") || commandExpanded[2].equals("!="))) {
            return CommandUse.IMAGE_COMPARE.getDescription();
        }

        BufferedImage image1 = (BufferedImage) EnvVariables.getVariable(commandExpanded[1]);
        BufferedImage image2 = (BufferedImage) EnvVariables.getVariable(commandExpanded[3]);

        if (commandExpanded[2].equals("==") && (ImageUtils.calculateMSE(image1, image2) < 0.05f)) {
            System.out.println(ImageUtils.calculateMSE(image1, image2));
            return ScriptExecutor.ExecuteScript(commandExpanded[4]);
        }

        if (commandExpanded[2].equals("!=") && (ImageUtils.calculateMSE(image1, image2) > 0.05f)) {
            System.out.println(ImageUtils.calculateMSE(image1, image2));
            return ScriptExecutor.ExecuteScript(commandExpanded[4]);
        }
        return "";
    }
}
