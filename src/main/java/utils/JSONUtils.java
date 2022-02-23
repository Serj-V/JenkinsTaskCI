package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.JSONPersonModel;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.NotFoundException;

public class JSONUtils {

    public static boolean fileIsJson(String file){
        try {
            new JSONParser().parse(file);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String deserializationObject(JSONPersonModel jsonPersonModel){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(jsonPersonModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }
}
