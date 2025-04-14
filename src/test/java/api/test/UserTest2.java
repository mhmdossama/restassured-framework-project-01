package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserTest2 {
    // setup data for user

    Faker faker = new Faker();
    User userPayload = new User();
    public Logger logger;

    @BeforeClass
    public void setUserPayload(){
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
//        userPayload.setUserStatus(1);

        //logs
        logger = LogManager.getLogger(UserTest2.class);


    }

    // Test post user
    @Test(priority = 1)
    public void testPostUser() {
        logger.info("Creating user");
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().body();
        assertThat("The status code is not equal to 200 ", response.statusCode(), equalTo(200));
        assertThat("The message is not equal to the user id", response.jsonPath().get("message"), equalTo(String.valueOf(userPayload.getId())));
        logger.info("User created successfully with username: " + userPayload.getUsername());
    }

    // Test get user
    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("Getting user by username");
        Response response = UserEndPoints2.getUserByName(userPayload.getUsername());
        response.then().log().body();
        assertThat("The status code is not equal to 200 ", response.statusCode(), equalTo(200));
        assertThat("The username is not equal to the expected value", response.jsonPath().get("username"), equalTo(userPayload.getUsername()));

        logger.info("User retrieved successfully with username: " + userPayload.getUsername());
    }

    // Test update user
    @Test(priority = 3)
    public void testUpdateUser() {
        logger.info("Updating user");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        Response response = UserEndPoints2.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().body();
        assertThat("The status code is not equal to 200 ", response.statusCode(), equalTo(200));

        //Verify the updated user
        Response getResponse = UserEndPoints2.getUserByName(userPayload.getUsername());
        getResponse.then().log().body();
        assertThat("The status code is not equal to 200 ", getResponse.statusCode(), equalTo(200));
        assertThat("The first name is not equal to the expected value", getResponse.jsonPath().get("firstName"), equalTo(userPayload.getFirstName()));
        assertThat("The last name is not equal to the expected value", getResponse.jsonPath().get("lastName"), equalTo(userPayload.getLastName()));
        logger.info("User updated successfully with username: " + userPayload.getUsername());
    }

    // Test delete user
    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("Deleting user");
        Response response = UserEndPoints2.deleteUser(userPayload.getUsername());
        response.then().log().body();
        assertThat("The status code is not equal to 200 ", response.statusCode(), equalTo(200));

        logger.info("User deleted successfully with username: " + userPayload.getUsername());
    }
}
