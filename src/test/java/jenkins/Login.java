package jenkins;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SessionConfig;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Login {
    public static RequestSpecification requestSpecification;
    public static SessionFilter sessionFilter;
    public static String name = "wulei_test";

    @BeforeClass
    public static void testBeforeClass(){
        useRelaxedHTTPSValidation();
        RestAssured.baseURI = "http://jenkins.testing-studio.com:8081";

        //把session放在filter中，后续可以直接引用。
        RestAssured.config = RestAssured.config().sessionConfig(
                new SessionConfig().sessionIdName("JSESSIONID.33a249ba"));
        System.out.println(RestAssured.config.toString());
        sessionFilter = new SessionFilter();

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addCookie("screenResolution","1440x900");
        requestSpecification = requestSpecBuilder.build();
        login();
    }

    public static void login(){

        Response response = given()
//                .log().all()
                .filter(sessionFilter).spec(requestSpecification)
                .formParam("j_username","wulei")
                .formParam("j_password","wulei")
                .formParam("from","%2F")
//                .formParam("Jenkins-Crumb","d392351232f5e13cf4ff638e5d032b21")
//                .formParam("json","%7B%22j_username%22%3A+%22wulei%22%2C+%22j_password%22%3A+%22wulei%22%2C+%22remember_me%22%3A+false%2C+%22from%22%3A+%22%2F%22%2C+%22Jenkins-Crumb%22%3A+%22d392351232f5e13cf4ff638e5d032b21%22%7D")
                .formParam("Submit","%E7%99%BB%E5%BD%95")
                .when()
                .post("/j_acegi_security_check")
                .then()
//                .log().all()
                .statusCode(302)
                .cookie("JSESSIONID.33a249ba",notNullValue())
                .header("Location",equalTo(RestAssured.baseURI+"/%2F"))
                .extract().response();

        given().when().get("/").then().statusCode(200);
    }
}
