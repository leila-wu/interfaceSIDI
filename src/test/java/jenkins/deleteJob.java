package jenkins;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class deleteJob extends Login{

    @Test
    public void deleteJob(){
        //删除job首先要打开job工程
//        given().log().all()
//                .when().get("/job/"+name)
//                .then().statusCode(200).body("html.head.title",equalTo("wulei_test [Jenkins]"));

        given().log().all()
                .filter(sessionFilter)
                .spec(requestSpecification)
                .formParam("Jenkins-Crumb","abc59ae2f0592643f26b9aba63b9afb7")
                .when()
                .post("/job/"+name+"/doDelete")
                .then()
                .statusCode(302);

        given().log().all()
                .when().get("/")
                .then().statusCode(200).body("html.head.title",equalTo("Dashboard [Jenkins]"));


    }
}
