package org.example;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class TestOnLocalAPI {
    @Ignore
    @Test
    public void get(){
        baseURI = "http://localhost:3000";
        given().get("/users").then().statusCode(200).log().all();
    }

    @Ignore
    @Test
    public void post(){
        JSONObject request = new JSONObject();
        request.put("firstName", "Thomas");
        request.put("lastName", "Edison");
        request.put("subjectId", 1);

        baseURI = "http://localhost:3000";
        given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().post("/users").
                then().statusCode(201);
    }

    @Ignore
    @Test
    public void put(){
        JSONObject request = new JSONObject();
        request.put("firstName", "Albert");
        request.put("lastName", "Edison");
        request.put("subjectId", 2);

        baseURI = "http://localhost:3000";
        given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().put("/users/4").
                then().statusCode(200);
    }

    @Ignore
    @Test
    public void patch(){
        JSONObject request = new JSONObject();
        request.put("lastName", "Doe");


        baseURI = "http://localhost:3000";
        given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().patch("/users/4").
                then().statusCode(204);
    }

    @Test
    public void delete(){
        baseURI = "http://localhost:3000";

        when().delete("/users/4").then().statusCode(200);
    }
}
