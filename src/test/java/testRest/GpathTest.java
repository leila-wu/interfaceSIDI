package testRest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GpathTest {
    @BeforeClass
    public static void beforeClass(){
        //https 不校验证书ssl
        useRelaxedHTTPSValidation();
    }

    @Test
    public void baidu(){
        //请求百度html页面
        given()
        .when()
                .get("http://www.baidu.com")
        .then()
                .log().all()
                .statusCode(200)
                .body("html.head.title",equalTo("百度一下，你就知道"))
        ;
    }

    @Test
    public void douban(){
        //  请求豆瓣的json
        //  https://developers.douban.com/wiki/?title=guide
        when()
                .post("https://api.douban.com/v2/book/1220562")
        .then()
                .log().all()
                .statusCode(200)
                .body("title",equalTo("满月之夜白鲸现"))
                .body("rating.average", lessThanOrEqualTo("8.0"));
    }

    @Test
    public void douban2(){
        //查看图书的标签总数大于6
        when().get("https://api.douban.com/v2/book/1220562/tags")
                .then().log().all()
//                .body("tags.count.size()",lessThan(6));
                .body("tags.count.size()", greaterThan(6));
    }

    @Test
    public void douban3(){
        //查看菜和头在豆瓣收藏书籍的标签信息
        when().get("https://api.douban.com/v2/book/user/hecaitou/tags")
                .then().statusCode(200)
                .body("tags.findAll {it.count == 2}.size()",equalTo(2))
                .body("tags.title[-1]",equalTo("四星上将"))
                .time(lessThan(10000L))
        ;
    }

    @Test
    public void testWebService(){
        //xml形式，天气预报
        Response response =
        when().get("http://api.map.baidu.com/telematics/v3/weather?location=武汉&ak=8IoIaU655sQrs95uMWRWPDIa")
                .then().statusCode(200).log().all()
                .body("CityWeatherResponse.error",equalTo("0"))
                .body("CityWeatherResponse.results.weather_data.date.size()",equalTo(4))
                .extract().response()
        ;

        String s1 = response.path("CityWeatherResponse.results.weather_data.date[1]");
        System.out.println(s1);
    }

    @Test
    public void baseurl(){
        RestAssured.baseURI = "https://api.douban.com";
                //查看菜和头在豆瓣收藏书籍的标签信息
        when().get("/v2/book/user/hecaitou/tags")
                .then().statusCode(200)
                .body("tags.findAll {it.count == 2}.size()",equalTo(2))
                .body("tags.title[-1]",equalTo("四星上将"))
                .time(lessThan(2000L))
        ;
        }

}
