package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import static utils.LineUtils.getIntValue;

public abstract class ConfigUtils {
    public static String getConfigString(String key){
        ISettingsFile environment = new JsonSettingsFile("MyTestConfig.json");
        return environment.getValue("/"+key).toString();
    }

    public static int getConfigInt(String key){
        ISettingsFile environment = new JsonSettingsFile("MyTestConfig.json");
        return getIntValue(environment.getValue("/"+key).toString());
    }
}
