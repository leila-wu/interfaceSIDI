package testRest;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.config.SessionConfig;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


import java.util.Base64;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

//多个用例需要filter带上参数，少部分不需要filter
//方法一： 在需要的filter中带上filter,不需要的不带
//方法二： 在不需要filter的用例中加上RestAssured.reset();
//spec也可以设置公用请求，但是不能篡改请求
public class filterTest {

    @BeforeClass
    public static void beforeClass(){
        useRelaxedHTTPSValidation();
        RestAssured.proxy("127.0.0.1",8888);

        //把filters放在beforeclass,则所有用例都会在请求头中增加cookie
        //Response filter(FilterableRequestSpecification var1, FilterableResponseSpecification var2, FilterContext var3);
        //req: request 请求
        //res: response 响应
        RestAssured.filters( (req, res, ctx)-> {
                    req.cookie("test","test1234");
                    //ctx.next(req,res)表示的是响应
                    return ctx.next(req, res);
                } );
    }

    @Ignore("Not Ready to Run")
    @Test
    public void baidu() {
        //截取发送的请求，对request修改，增加cookile
        given()
                //在请求中使用filter，加上通用的请求参数。
//                .filter( (req, res, ctx)-> {
//                    req.cookie("test","test1234");
//                    return ctx.next(req, res);
//                } )
                .log().all()
                .when()
                .get("http://www.baidu.com")
                .then()
//                .log().all()
                .statusCode(200)
                .body("html.head.title", equalTo("百度一下，你就知道"));
    }

    @Test
    public void test1(){
        //就不用filters
        RestAssured.reset();
        given().log().all()
                .when()
                .get("http://www.baidu.com")
                .then()
                .log().all()
                .statusCode(200).body("html.head.title",equalTo("百度一下，你就知道"))
        ;
    }

    @Test
    public void testBase64(){

        //截取返回值的秘闻，使用filter解密
        // hogwarts : 123456
        given().
                auth().basic("hogwarts","123456").
                log().all().
                filter((req, res, ctx) ->{
                    //ctx.next(req,res)表示的是响应
                    Response responseOri = ctx.next(req, res);
                    //使用filter进行base64的解密操作，返回请求是base64的密文
                    //方法一：
                    //把请求克隆给repsponsebuilder
                    ResponseBuilder responseBuilder = new ResponseBuilder().clone(responseOri);
                    //string.trim() 去掉字符串最后的空行
                    //String.replace 替换字符
                    responseBuilder.setBody(Base64.getDecoder().decode(responseOri.getBody().asString().trim().replace("\n",""))) ;
                    //把返回内容string得到的文本，转换为json
                    responseBuilder.setContentType(ContentType.JSON);
                    //方法二：
                    //取出返回的请求内容解密
                    // String raw = new String(Base64.getDecoder().decode(responseOri.body().asString().trim()));
                    // ResponseBuilder resBuilder = new ResponseBuilder().clone(responseOri);
                    // resBuilder.setBody(raw);

                    return responseBuilder.build();
                }).
                when()
                .get("http://shell.testing-studio.com:9002/sec.json")
                .then().log().all().statusCode(200)
                .body("topics.id[0]", equalTo(15150));
    }


    @Test
    public void testSessionFilter(){
        RestAssured.reset();
        RestAssured.baseURI = "http://jenkins.testing-studio.com:8081";
        //把session放在filter中，后续可以直接引用。
        RestAssured.config = RestAssured.config().sessionConfig(
                new SessionConfig().sessionIdName("JSESSIONID.33a249ba"));
        System.out.println(RestAssured.config.toString());
        SessionFilter sessionFilter = new SessionFilter();
        String screenResolution = "1440x900";

        Response response = given()
                .log().all()
//                .filter(sessionFilter)
                .cookie("JSESSIONID.33a249ba","node0b7p3sesjjql4fcd9gyhw3g9r736.node0")
                .cookie("screenResolution",screenResolution)
                .formParam("j_username","wulei")
                .formParam("j_password","wulei")
                .formParam("from","%2F")
                .formParam("Jenkins-Crumb","d392351232f5e13cf4ff638e5d032b21")
                .formParam("json","%7B%22j_username%22%3A+%22wulei%22%2C+%22j_password%22%3A+%22wulei%22%2C+%22remember_me%22%3A+false%2C+%22from%22%3A+%22%2F%22%2C+%22Jenkins-Crumb%22%3A+%22d392351232f5e13cf4ff638e5d032b21%22%7D")
                .formParam("Submit","%E7%99%BB%E5%BD%95")
                .when()
                .post("/j_acegi_security_check")
                .then()
                .log().all()
                .statusCode(302)
                .cookie("JSESSIONID.33a249ba",notNullValue())
                .header("Location",equalTo(RestAssured.baseURI+"/%2F"))
                .extract().response();

        given().when().get(RestAssured.baseURI).prettyPeek().then().statusCode(200);

    }



    //    @BeforeClass
//    public void setUp() throws Exception {
//        RestAssured.useRelaxedHTTPSValidation();
//        RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().
//                defaultCharsetForContentType("utf-8", "application/x-www-form-urlencoded")).
//                httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout", 2000).
//                        setParam("http.socket.timeout", 2000));
//    }
}
