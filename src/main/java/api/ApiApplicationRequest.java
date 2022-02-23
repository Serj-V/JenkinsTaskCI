package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.JSONPersonModel;
import models.ResponseJSONModel;
import models.UserModel;

import java.io.IOException;

import static utils.APIUtils.*;
import static utils.APIUtils.sendPostRequest;
import static utils.ConfigUtils.getConfigString;
import static utils.JSONUtils.deserializationObject;

public class ApiApplicationRequest {

    public static ResponseJSONModel RESPONSE_JSON = null;
    private static String POSTS_POSTFIX = "posts";
    private static String USERS_POSTFIX = "users";
    private static String BASE_HTTP = getConfigString("baseHttp");

    public static JSONPersonModel getJsonPerson(int number){
        RESPONSE_JSON = sendGetRequest(BASE_HTTP,POSTS_POSTFIX+"/"+number);
        JSONPersonModel person = null;
        try {
            person = new ObjectMapper().readValue(RESPONSE_JSON.getBody().toString(), JSONPersonModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    public static JSONPersonModel[] getJsonPersons(){
        RESPONSE_JSON = sendGetRequest(BASE_HTTP, POSTS_POSTFIX);
        JSONPersonModel[] persons = null;
        try {
            persons = new ObjectMapper().readValue(RESPONSE_JSON.getBody().toString(), JSONPersonModel[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public static UserModel getJsonUser(int number){
        RESPONSE_JSON = sendGetRequest(BASE_HTTP,USERS_POSTFIX+"/"+number);
        UserModel userModel = null;
        try {
            userModel = new ObjectMapper().readValue(RESPONSE_JSON.getBody().toString(), UserModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    public static UserModel[] getJsonUsers(){
        RESPONSE_JSON = sendGetRequest(BASE_HTTP, USERS_POSTFIX);
        UserModel[] userModels = null;
        try {
            userModels = new ObjectMapper().readValue(RESPONSE_JSON.getBody().toString(), UserModel[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userModels;
    }

    public static JSONPersonModel postJsonPerson(JSONPersonModel personPost){
        RESPONSE_JSON = sendPostRequest(BASE_HTTP, POSTS_POSTFIX, deserializationObject(personPost));
        JSONPersonModel person = null;
        try {
            person = new ObjectMapper().readValue(RESPONSE_JSON.getBody().toString(), JSONPersonModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
}
