package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static utils.LineUtils.getIntValue;

public class ConfigUtils {

    public static String getExpectedJson(String fileName){
        try {
            return FileUtils.readFileToString(new File("src/main/resources/"+fileName+".json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    public static String getConfigString(String key){
        ISettingsFile environment = new JsonSettingsFile("MyTestConfig.json");
        return environment.getValue("/"+key).toString();
    }

    public static int getConfigInt(String key){
        ISettingsFile environment = new JsonSettingsFile("MyTestConfig.json");
        return getIntValue(environment.getValue("/"+key).toString());
    }
}
