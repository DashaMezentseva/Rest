//package com.nixsolutions.rest;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//
//import io.restassured.RestAssured;
//import io.restassured.specification.RequestSpecification;
//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.BeforeEach;
//
//public class RestUserTest {
//    UserResource userResource;
//
//    @BeforeClass
//    public static void init() {
//        RestAssured.baseURI = "http://192.168.0.107";
//        RestAssured.port = 8888;
//    }
//
//    @org.junit.jupiter.api.Test
//    void getUser() {
//        RestAssured.baseURI = "http://192.168.0.107";
//        RestAssured.port = 8888;
//        RestAssured.get("/rest/users/16")
//            .then()
//            .body("firstName", equalTo("Admin"))
//            .body("lastName", equalTo("Admin"))
//            .body("login", equalTo("admin"))
//            .body("password", equalTo("admin"))
//            .and().statusCode(200);
//    }
//
//    @org.junit.jupiter.api.Test
//    void getNullUser() {
//        RestAssured.get("/rest/users/2")
//            .then()
//            .assertThat()
//            .statusCode(204);
//    }
//
//     @org.junit.jupiter.api.Test
//    void putUser() throws JSONException {
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("userId", 100L);
//        requestBody.put("firstName", "String");
//        requestBody.put("lastName", "String");
//        requestBody.put("login", "someRandomString");
//        requestBody.put("password", "String");
//        requestBody.put("email", "String" + "@gmail.com");
//        requestBody.put("birthday", "1111-11-11");
//        requestBody.put("roleId", 1L);
//
//        RequestSpecification request = RestAssured.given();
//        request.header("Content-Type", "application/json");
//
//        request.body(requestBody.toString())
//            .put("http://192.168.0.107:8888/rest/users/11345")
//            .then()
//            .statusCode(204);
//
//    }
//
//    @org.junit.jupiter.api.Test
//    void deleteUser() {
//        RequestSpecification request = RestAssured.given();
//        request.header("Content-Type", "application/json");
//        request.delete("http://192.168.0.107:8888/rest/users/11239");
//        request.then().statusCode(200);
//    }
//
//    @BeforeEach
//    void setUp() {
//        userResource = new UserResource();
//    }
//}
