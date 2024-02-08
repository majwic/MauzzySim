package mauzzysim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ScriptExecutor {

    private static final String filePath = "external-resources/scripts/";

    public static String ExecuteScript(String fileName) {
        File file = new File(filePath + fileName);

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String command;
            while ((command = bufferedReader.readLine()) != null) {
                String error = MauzzySimModel.runCommand(command);
                if (!error.isEmpty())
                    return fileName + " -> " + error;
            }
        } catch (IOException e) {
            return "No script exists";
        }
        return "";
    }
}
