package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileInputStream;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndPoints {


    public static Response createUser(User payload){
        Response response=given().log().all().header("Accept","application/json").contentType(ContentType.JSON).body(payload).when().post(Routes.post_url);

  return response;
    }

    public static Response readUser(String userName){
        Response response=given().log().all().pathParam("username",userName).when().get(Routes.get_url);

    return response;
    }

    public static Response updateUser(String userName,User payload){
        Response response=given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("username",userName).body(payload).when().put(Routes.get_url);

        return response;
    }


    public static Response deleteUser(String userName){
        Response response=given().pathParam("username",userName).when().get(Routes.delete_url);

        return response;
    }
}
