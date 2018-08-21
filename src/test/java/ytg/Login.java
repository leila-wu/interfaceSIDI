package ytg;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.config.SessionConfig;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
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

    //验证码：http://123.207.17.180:14005/nImgServlet?mobileKey=1534306707531&r=0.6517037763880074
    @BeforeClass
    public static void testBeforeClass(){
        useRelaxedHTTPSValidation();
        RestAssured.baseURI = "http://123.207.17.180:14005";

        //把session放在filter中，后续可以直接引用。
        RestAssured.config = RestAssured.config().sessionConfig(
                new SessionConfig().sessionIdName("JSESSIONID"));
        System.out.println(RestAssured.config.toString());
        sessionFilter = new SessionFilter();

        RestAssured.filters( (req, res, ctx) -> {
            Response responseOri = ctx.next(req, res);
            ResponseBuilder responseBuilder = new ResponseBuilder().clone(responseOri);
            responseBuilder.setBody(responseOri.asString().replace("<html>\n  <body>","").replace("</body>\n</html>","")) ;
            //把返回内容string得到的文本，转换为json
            responseBuilder.setContentType(ContentType.JSON);
            return responseBuilder.build();
        });

    }

    @Test
    public void login(){
        Response response = given()
                .log().all()
                .formParam("funcNo","1004903")
                .formParam("account","100038422")
                .formParam("pwd_value","123456")
                .formParam("login_type","4")
                .formParam("account_type","4")
                .formParam("verify_code","1234")
                .formParam("mobileKey","1534399059142")
                .formParam("op_source","1")
        .when()
                .post("/servlet/json")
        .then()
                .log().all()
                .statusCode(200)
                .body("error_no", equalTo("0"))
                .body("results.name",equalTo("刘建波"))
                .extract().response();

//        given().when().get("/").then().statusCode(200);
    }

    public static void vcode(){

    }
}
