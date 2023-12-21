package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ApiAutomation {
    @Test
    public void test1(){
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Response: " + response.asString());
        System.out.println("Body: " +response.getBody().asString());
        System.out.println("Time taken: " +response.getTime());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test2(){
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).body("data[1].id",equalTo(8)).log().all();
    }
}
