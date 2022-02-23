import aquality.selenium.browser.AqualityServices;
import models.JSONPersonModel;
import models.UserModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static api.ApiApplicationRequest.*;
import static utils.ConfigUtils.*;
import static utils.ModelsUtils.*;
import static utils.JSONUtils.fileIsJson;
import static utils.LineUtils.getRandomString;

public class APITests {

    @Test
    public void sendGetPostRequests (){
        SoftAssert softAssert = new SoftAssert();
        AqualityServices.getLogger().info("Send a GET request to get all posts (/posts)");
        JSONPersonModel[] persons = getJsonPersons();
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.OK.getStatus(),
                "status code is not "+StatusCodes.OK.getStatus()+" status is:"+RESPONSE_JSON.getStatusCode());
        Assert.assertTrue(fileIsJson(RESPONSE_JSON.getBody().toString()), "file the file is not json");
        Assert.assertTrue(dataIsSortedById(persons), "the tags are not in ascending order");

        AqualityServices.getLogger().info("Send a GET request to receive post /posts/99");
        JSONPersonModel actualPerson = getJsonPerson(getConfigInt("personGet"));
        JSONPersonModel expectedPerson = getJsonPersonFromFile(getExpectedJson("expectedPost99"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.OK.getStatus(),
                "status code is not "+StatusCodes.OK.getStatus()+" status is:"+RESPONSE_JSON.getStatusCode());
        softAssert.assertEquals(actualPerson.getUserId(),expectedPerson.getUserId(),"userId don't match");
        softAssert.assertEquals(actualPerson.getId(),expectedPerson.getId(), "id don't match");
        softAssert.assertFalse(actualPerson.getBody().isEmpty(), "body is empty");
        softAssert.assertFalse(actualPerson.getTitle().isEmpty(), "title is empty");

        AqualityServices.getLogger().info("Send a GET request to receive post #150");
        JSONPersonModel jsonPersonModelEmpty = getJsonPerson(getConfigInt("nullPersonGet"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.EMPTY.getStatus(),
                "status code is not "+StatusCodes.EMPTY.getStatus()+" status is:"+RESPONSE_JSON.getStatusCode());
        Assert.assertTrue(getEmptyBodyPerson(jsonPersonModelEmpty), "the request body is not empty");

        AqualityServices.getLogger().info("Send a POST request to create a record /posts.In the request body," +
                " add randomly generated text to the body and title fields.The user Id field must contain 1.");
        JSONPersonModel expectedPersonPost = new JSONPersonModel(1,101, getRandomString(), getRandomString());
        JSONPersonModel actualJsonPersonModelPost = postJsonPerson(expectedPersonPost);
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.POST.getStatus(),
                "status code is not "+StatusCodes.POST.getStatus()+" status is:"+RESPONSE_JSON.getStatusCode());
        softAssert.assertEquals(expectedPersonPost.getTitle(), actualJsonPersonModelPost.getTitle(), "the title does not match");
        softAssert.assertEquals(expectedPersonPost.getBody(), actualJsonPersonModelPost.getBody(), "the body does not match");
        softAssert.assertEquals(expectedPersonPost.getUserId(), actualJsonPersonModelPost.getUserId(), "the UserID does not match");
        softAssert.assertEquals(expectedPersonPost.getId(), actualJsonPersonModelPost.getId(), "the id does not match");

        AqualityServices.getLogger().info("Send a GET request to get users /users");
        UserModel[] userModels = getJsonUsers();
        UserModel expectedUserModel = getJsonUserFromFile(getExpectedJson("expectedUser5"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.OK.getStatus(),
                "status code is not "+StatusCodes.OK.getStatus()+" status is:"+RESPONSE_JSON.getStatusCode());
        Assert.assertTrue(fileIsJson(RESPONSE_JSON.getBody().toString()), "file the file is not json");
        softAssert.assertEquals(expectedUserModel, userModels[getConfigInt("numExpectedUser")-1],
                "The user with the number "+getConfigInt("numExpectedUser")+" does not match the expected user with this number");

        AqualityServices.getLogger().info("Send a GET request to get user 5 /users/5");
        UserModel userModel = getJsonUser(getConfigInt("numExpectedUser"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.OK.getStatus(),
                "status code is not "+StatusCodes.OK.getStatus()+" status is:"+RESPONSE_JSON.getStatusCode());
        softAssert.assertEquals(userModels[getConfigInt("numExpectedUser")-1], userModel,
                "the user does not match with the user from the list number "+getConfigInt("numExpectedUser")+" from the previous request");
        softAssert.assertAll();
    }
}
