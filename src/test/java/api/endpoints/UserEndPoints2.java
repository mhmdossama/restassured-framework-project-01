package api.endpoints;

import api.payload.User;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    ResourceBundle rb = ResourceBundle.getBundle("routes");

    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");

    }
    // UserEndPoints Class is used to define all the CRUD operations for the User module
    // Create User
    public static String createUser= getURL().getString("post_URL");

    public static Response createUser(User payload) {
        // Code to create user

        return given()
             .contentType("application/json")
             .accept("application/json")
             .body(payload)
             .when().post(createUser);
    }
    // Get User
    public static String getUserByName = getURL().getString("get_URL");
    public static Response getUserByName(String username) {
        // Code to get user by username

        return given()
                .pathParam("username", username)
                .accept("application/json")
                .when().get(getUserByName);
    }
    // Update User
    public static String updateUser = getURL().getString("put_URL");
    public static Response updateUser(String username, User payload) {
        // Code to update user

        return given()
                .pathParam("username", username)
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .when().put(updateUser);
    }
    // Delete User
    public static String deleteUser = getURL().getString("delete_URL");
    public static Response deleteUser(String username) {
        // Code to delete user

        return given()
                .pathParam("username", username)
                .accept("application/json")
                .when().delete(deleteUser);
    }

}
