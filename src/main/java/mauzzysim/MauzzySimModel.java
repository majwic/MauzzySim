package mauzzysim;

import java.awt.*;

public class MauzzySimModel {

    public String runCommand(String command) {
        if (command.isEmpty()) {
            return "";
        }

        String[] commandExpanded = command.split("\\s+");
        String commandKeyword = commandExpanded[0];
        int x = 0, y = 0;

        switch(commandKeyword) {
            case "moveTo":
                return Command.moveTo(commandExpanded);
        }
        return "";
    }
}
