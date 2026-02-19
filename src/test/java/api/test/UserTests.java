package api.test;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class UserTests {

    Faker faker;
    User userPayload;
    public Logger logger;
    @BeforeClass
    public void setUp(){
        logger= LogManager.getLogger(this.getClass());
        faker=new Faker();
       userPayload=new User();
      userPayload.setId(faker.idNumber().hashCode());
      userPayload.setUsername(faker.name().username());
      userPayload.setFirstName(faker.name().firstName());
      userPayload.setLastName(faker.name().lastName());
      userPayload.setEmail(faker.internet().emailAddress());
      userPayload.setPassword(faker.internet().password(5,10));
      userPayload.setPhone(faker.phoneNumber().phoneNumber());

    }
    @Test(priority = 0)
    public  void testPostUser() throws IOException {
//using routes class where giving base uri and endpoints
       //this is to test webhooks
      
        logger.debug("***************** creating user *************************");
        Response response= UserEndPoints.createUser(userPayload);
        response.then().log().all();
        System.out.println(response.statusCode());
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***************** user created *************************");
    }

    @Test(priority = 1)
    public void testGetUser(){
        logger.debug("***************** reading user info");
            Response response= UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***************** verifying the data is retrived successfully ******************");

    }
    @Test(priority = 2)
    public void testPutUser(){
        logger.debug("***************** updating the  user ****************");
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response= UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //checking data after update
        Response response1= UserEndPoints.readUser(this.userPayload.getUsername());
        response1.then().log().all();
        Assert.assertEquals(response1.getStatusCode(),200);


    }

    @Test(priority = 3)
    public void testDeleteUser(){

        logger.debug("***************** deleting user ***************************");
        Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
