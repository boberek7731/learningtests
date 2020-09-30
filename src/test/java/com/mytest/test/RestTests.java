package com.mytest.test;

import com.mytest.test.util.MyTestClass;
import com.mytest.test.util.ResponseClass;
import com.mytest.test.util.Slideshow;
import com.mytest.test.util.WrapperXMLClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import java.io.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestTests {

    @BeforeSuite
    public void setUp() {

//        RestAssured.baseURI = "http://localhost/";
//        RestAssured.port = 80;
        RestAssured.baseURI = "http://httpbin.org/";
    }

    @AfterSuite
    public void cleanUp() {
        RestAssured.reset();
    }

    @Test
    public void simpleRestTest() {
        when()
                .get("http://httpbin.org/get").
                then()
                .statusCode(200);
    }

    @Test
    public void delete200CodeTest() {
        when().delete("/delete").then().statusCode(200);
    }

    @Test
    public void get200CodeTest() {
        Response response = when().get("http://httpbin.org/status/200");
        assertEquals( 200, response.getStatusCode());
    }

    @Test
    public void patch200CodeTest() {
        when().patch("/patch").then().statusCode(200);
    }

    @Test
    public void post200CodeTest() {
        when().post("/post").then().statusCode(200);
    }

    @Test
    public void put200CodeTest() {
        when().put("/put").then().statusCode(200);
    }

    @Test
    public void basicAuth200TestCode() {
        String user = "user";
        String password = "password";
        given()
                .auth()
                .preemptive()
                .basic(user, password)
                .get("/basic-auth/user/password")
                .then().statusCode(200);
    }

    @Test
    public void bearerAuth200CodeTest() {
       //TODO autentykacja
    }

    @Test
    public void shouldReturnMyIP() {
        String myIp = "185.125.227.43";
        when().get("/ip").then().body("origin", equalTo(myIp));
    }

    @Test
    public void shouldDecodeBase64String() {
        String base64String = "SFRUUEJJTiBpcyBhd2Vzb21l";
        when().get("/base64/SFRUUEJJTiBpcyBhd2Vzb21l").then().body(equalTo("HTTPBIN is awesome"));
    }

    @Test
    public void getImage() {
        Response response = when().get("image/jpeg");

        Response response1 = given().header("accept", "image/png").when().get("/image");
        assertTrue(response1.getStatusCode() == 200);

        byte[] bytes = response1.getBody().asByteArray();

        try (OutputStream outputStream = new FileOutputStream(new File("/Users/sg0221974/dev/mydev/seleniumtest/pngfile.png"))){
            outputStream.write(bytes);
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
    }

    @Test
    public void anythingPostWithJsonTest() {
        JSONObject requestData = new JSONObject();
        requestData.put("MyName", "Michal");
        requestData.put("MyLastName", "Bober");
        JSONObject put = new JSONObject();
        put.put("Another", "Node");
        requestData.put("additional", put);

        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(requestData.toJSONString());

        Response response = request.pathParam("anything","test").post("/anything/{anything}").thenReturn();
        response.then().body("json.MyName", equalTo("Michal"));
        response.then().body("json.MyLastName", equalTo("Bober"));
        response.then().body("json.additional.Another", equalTo("Node"));
    }

    @Test
    public void jsonSerializationTest() {
        Response response = when().get("/json").thenReturn();
        ResponseClass responseObject = response.body().as(ResponseClass.class);

        Slideshow slideshow = responseObject.getSlideshow();
        assertEquals("Yours Truly", slideshow.getAuthor());
        assertEquals("date of publication", slideshow.getDate());
        assertEquals(2, slideshow.getSlides().size());
        assertEquals("Sample Slide Show", slideshow.getTitle());

    }

    @Test
    public void hiddenBasicAuth() {
        given().auth().preemptive().basic("testuser", "testpass")
                .when()
                .pathParam("user", "testuser")
                .pathParam("passwd","testpass")
                .log().all()
                .get("/hidden-basic-auth/{user}/{passwd}")
                .then().statusCode(200);
    }

    @Test
    public void digest_authTest() {
        given().auth().digest("testuser","testpass")
                .when()
                .pathParam("qop","auth")
                .pathParam("user","testuser")
                .pathParam("passwd","testpass")
                .log().all()
                .get("/digest-auth/{qop}/{user}/{passwd}")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .body("user",equalTo("testuser"));
    }


    @Test
    public void md5AuthTest() {
        given().auth().digest("testuser","testpass")
                .when()
                .pathParam("qop","auth")
                .pathParam("user","testuser")
                .pathParam("passwd","testpass")
                .pathParam("algorithm","SHA-256")
                .log().all()
                .get("/digest-auth/{qop}/{user}/{passwd}/{algorithm}")
                .then().statusCode(200);
    }

    @Test
    public void xmlSerializationTest() {
        MyTestClass testClass = new MyTestClass("Michal","Bober");
        given().contentType(ContentType.XML).body(testClass).log().all().when().post("/anything").then().statusCode(200);
    }



}
