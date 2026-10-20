package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTesting {

    Faker faker;
    User userPayload;
    public Logger logger;
    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.number().numberBetween(1,100000));
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(8,14));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //generating some logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testingPostUser()
    {
        logger.info("*********** Creating User ***********");

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("*********** User Created ************");
    }

    @Test(priority = 2)
    public void testingGetUser()
    {
        logger.info("********** Reading User Info ***********");

        Response response = UserEndPoints.getUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("*********** Info Displayed ***********");
    }

    @Test(priority = 3)
    public void testingUpdateUser()
    {
        logger.info("*********** Updating User ***********");

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("*********** User Updated ***********");

        //checking the data after update

        logger.info("*********** Checking the updates ***********");

        Response responseAfterUpdate = UserEndPoints.getUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();

        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);

        logger.info("*********** Updates Checked ***********");
    }

    @Test(priority = 4)
    void testingDeleteUser()
    {
        logger.info("*********** Deleting User ***********");

        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("*********** User Deleted ***********");
    }

}
