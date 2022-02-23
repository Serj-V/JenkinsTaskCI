package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.JSONPersonModel;
import models.UserModel;

import java.io.IOException;

public class ModelsUtils {

    public static boolean getEmptyBodyPerson(JSONPersonModel person){
        try {
            person.getBody().isEmpty();
            return false;
        }catch (NullPointerException e){
            return true;
        }
    }

    public static boolean dataIsSortedById(JSONPersonModel[] persons){
        for(int i = 0; i < persons.length - 1; i++){
            if(persons[i].getId() >= persons[i+1].getId()) {
                return false;
            }
        }
        return true;
    }

    public static UserModel getJsonUserFromFile(String jsonOnFile){
        UserModel userModel = null;
        try {
            userModel = new ObjectMapper().readValue(jsonOnFile, UserModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    public static JSONPersonModel getJsonPersonFromFile(String jsonOnFile){
        JSONPersonModel person = null;
        try {
            person = new ObjectMapper().readValue(jsonOnFile, JSONPersonModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
}
