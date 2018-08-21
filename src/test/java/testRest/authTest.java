package testRest;

import io.restassured.builder.ResponseBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class authTest {
    //auth ,认证授权

    @Test
    public void testAuth1(){
        // hogwarts : 123456
        given().get("http://shell.testing-studio.com:9002")
                .then().statusCode(200)
                .log().all()
        ;
    }

    @Test
    public void testAuth(){

        given()
                .auth().basic("hogwarts","123456")
                .get("http://shell.testing-studio.com:9002")
                .then().statusCode(200)
                .log().all()
        ;
    }

}
