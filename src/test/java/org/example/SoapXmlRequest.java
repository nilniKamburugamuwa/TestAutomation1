package org.example;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SoapXmlRequest {

    @Test
    public void validateSoapXMP() throws IOException {
        File file = new File("./SoapRequest/text.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
        baseURI = "http://dneonline.com";
        given().contentType("text/xml").
                accept(ContentType.XML).
                body(requestBody).
                when().post("/calculator.asmx").
                then().statusCode(500);
    }
}
