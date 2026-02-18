package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2{

    public static ResourceBundle getUrl() throws IOException {
        ResourceBundle routes=ResourceBundle.getBundle("config");
        return routes;
    }
    public static Response createUser(User payload) throws IOException {
       String post_url=getUrl().getString("post_url");
        Response response=given().log().all().header("Accept","application/json").contentType(ContentType.JSON).body(payload).when().post(post_url);
  return response;
    }

    public static Response readUser(String userName) throws IOException {
        String get_url=getUrl().getString("get_url");
        Response response=given().log().all().pathParam("username",userName).when().get(get_url);

    return response;
    }

    public static Response updateUser(String userName,User payload) throws IOException {
        String put_url=getUrl().getString("put_url");
        Response response=given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("username",userName).body(payload).when().put(put_url);

        return response;
    }


    public static Response deleteUser(String userName) throws IOException {
        String delete_url=getUrl().getString("delete_url");
        Response response=given().pathParam("username",userName).when().get(delete_url);

        return response;
    }
}
