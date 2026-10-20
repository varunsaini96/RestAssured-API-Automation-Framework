package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDriverTests {

    User payloadData;

    @Test(priority = 1, dataProvider = "ExcelData", dataProviderClass = DataProviders.class)
    public void testingCreateUsers(String userID, String userName, String fname, String lname, String userEmail, String password, String phone)
    {
        payloadData = new User();
        payloadData.setId((Integer.parseInt(userID)));
        payloadData.setUsername(userName);
        payloadData.setFirstName(fname);
        payloadData.setLastName(lname);
        payloadData.setEmail(userEmail);
        payloadData.setPassword(password);
        payloadData.setPhone(phone);

        Response response = UserEndPoints.createUser(payloadData);

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2, dependsOnMethods = "testingCreateUsers",dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testingDeleteUsers(String username)
    {
        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
