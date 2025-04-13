package api.endpoints;

public class Routes {
    // Routes class is used to define all the endpoints of the API

    // Base URL
    public static String baseURL = "https://petstore.swagger.io/v2";

    //User Module
    public static String createUser = baseURL+"/user";
    public static String getUserByName = baseURL+"/user/{username}";
    public static String updateUser = baseURL+"/user/{username}";
    public static String deleteUser = baseURL+"/user/{username}";

    //Store Module

    //Pet Module

}
