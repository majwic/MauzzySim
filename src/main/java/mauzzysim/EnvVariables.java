package mauzzysim;

import java.util.HashMap;
import java.util.Map;

public class EnvVariables {
    private static final Map<String, Object> variableMap = new HashMap<>();

    public static void setVariable(String variableName, Object value) {
        variableMap.put(variableName, value);
    }

    public static Object getVariable(String variableName) {
        return variableMap.get(variableName);
    }

    public static boolean containsVariable(String variableName, Class<?> expectedClass) {
        if (!variableMap.containsKey(variableName)) {
            return false;
        }

        Class<?> storedClass = variableMap.get(variableName).getClass();

        return storedClass.equals(expectedClass);
    }
}
