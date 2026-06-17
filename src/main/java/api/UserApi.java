package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");
    }
    public static Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login");
    }
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }

    public static String getAccessToken(Response response) {
        return response.jsonPath().getString("accessToken");
    }

    public static boolean isUserDeleted(Response response) {
        return response.getStatusCode() == 202;
    }
}