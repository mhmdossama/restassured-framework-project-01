package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DDtest {
    // This is a data-driven test class
    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void postUser(String userID, String username, String firstName, String lastName, String email, String password, String phone) {
        User userPayload = new User();
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        // Call the API to create user
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().body();
        assertThat("The status code is not equal to 200 ", response.statusCode(), equalTo(200));
    }

    @Test(dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String username) {
        Response response = UserEndPoints.deleteUser(username);
        response.then().log().body();
        assertThat("The status code is not equal to 200 ", response.statusCode(), equalTo(200));
    }
}