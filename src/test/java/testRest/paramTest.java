package testRest;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class paramTest {

    @Ignore("Not Ready to Run")
    @Test
    public void testBaidu() {
        //带json传参数发送
        Map<String, Object> data = new HashMap<>();
        data.put("x", 1);
        data.put("y", "xxx");

        given()
                .contentType(ContentType.JSON).body(data).proxy("127.0.0.1", 8888)
                .when()
                .post("https://www.baidu.com/")
                .then()
                .statusCode(200);

    }

    @Ignore("Not Ready to Run")
    @Test
    public void testParm1() {
        useRelaxedHTTPSValidation();
        given().param("x", "111").and().param("y", 222)
                .proxy("127.0.0.1", 8888)
                .when().get("https://www.baidu.com/s")
                .then().statusCode(200);
    }

    @Ignore("Not Ready to Run")
    @Test
    public void testParm2() {
        useRelaxedHTTPSValidation();
        given().param("x", "ooo", "y","iiii")
                .proxy("127.0.0.1", 8888)
                .when().get("https://www.baidu.com/s")
                .then().statusCode(200);
    }

    @Ignore("Not Ready to Run")
    @Test
    public void testParm3() {
        useRelaxedHTTPSValidation();
        Map<String, Object> params = new HashMap<>();
        params.put("a", "333");
        params.put("b", "444");

        given().log().all()
                .params(params)
                .proxy("127.0.0.1", 8888)
                .when().get("https://www.baidu.com/s").prettyPeek()
                .then().statusCode(200)
        ;
    }

    @Ignore("Not Ready to Run")
    @Test
    public void testParm4() {
        //路径参数

        given().log().all()
                .pathParam("a","123")
                .proxy("127.0.0.1", 8888)
                .when().get("https://www.baidu.com/{a}").prettyPeek()
                .then().statusCode(200)
        ;
    }

    @Ignore("Not Ready to Run")
    @Test
    public void testParm6() {
        //路径参数2
        given().log().all()
                .proxy("127.0.0.1", 8888)
                .when().get("https://www.baidu.com/s/{x}","ff")
                .then().statusCode(200);
    }



    @Ignore("Not Ready to Run")
    @Test
    public void testChanDao1() {
        //cookie设置
        //表单设置
        given().log().all()
                .cookie("lang", "zh-cn").cookie(" device", "desktop")
                .cookie(" theme", "default").cookie(" bugModule", "0")
                .cookie(" preBranch", "0").cookie(" qaBugOrder", "id_desc")
                .cookie(" ajax_quickJump", "on").cookie(" lastProject", "969")
                .cookie(" preProjectID", "969").cookie(" moduleBrowseParam", "0")
                .cookie(" productBrowseParam", "0").cookie(" projectTaskOrder", "status%2Cid_desc")
                .cookie(" preProductID", "116").cookie(" lastProduct", "116")
                .cookie(" windowWidth", "1255").cookie(" windowHeight", "803")
                .cookie(" Hm_lvt_11b88b9faf7b3a70c7ad4d0917b9165a", "1532333639")
                .cookie(" zentaosid", "rk6pqast1spsoukm3kfksvg3s0")
                .formParam("account", "wulei")
                .formParam("password", "Admin@123")
                .formParam("referer", "http%3A%2F%2Fwww.thinkive.cn%3A10000%2Fzentao%2Fmy%2F")
                .when()
                .post("http://www.thinkive.cn:10000/zentao/user-login.html")
                .then().statusCode(200)
                .body("html.head.script", containsString("/zentao/index.html"))
                .log().all()
        ;
    }

    @Ignore("Not Ready to Run")
    @Test
    public void testChanDao2() {

        Cookie cookie1 = new Cookie.Builder("lang","zh-cn")
                .setComment("some comment").build();
        Cookie cookie2 = new Cookie.Builder("device","desktop")
                .setComment("some comment").build();
        Cookie cookie3 = new Cookie.Builder("bugModule","0")
                .setComment("some comment").build();
        Cookie cookie4 = new Cookie.Builder("theme","default")
                .setComment("some comment").build();
        Cookie cookie5 = new Cookie.Builder("ajax_quickJump","on")
                .setComment("some comment").build();
        Cookies cookies = new Cookies(cookie1,cookie2,cookie3,cookie4,cookie5);
        //cookie设置
        //表单设置
        given().log().all()
                .cookies(cookies)
                .cookie(" lastProject", "969")
                .cookie(" preProjectID", "969").cookie(" moduleBrowseParam", "0")
                .cookie(" productBrowseParam", "0").cookie(" projectTaskOrder", "status%2Cid_desc")
                .cookie(" preProductID", "116").cookie(" lastProduct", "116")
                .cookie(" windowWidth", "1255").cookie(" windowHeight", "803")
                .cookie(" Hm_lvt_11b88b9faf7b3a70c7ad4d0917b9165a", "1532333639")
                .cookie(" zentaosid", "rk6pqast1spsoukm3kfksvg3s0")
                .formParam("account", "wulei")
                .formParam("password", "Admin@123")
                .formParam("referer", "http%3A%2F%2Fwww.thinkive.cn%3A10000%2Fzentao%2Fmy%2F")
                .when()
                .post("http://www.thinkive.cn:10000/zentao/user-login.html")
                .then().statusCode(200)
                .body("html.head.script", containsString("/zentao/index.html"))
                .log().all()
        ;
    }

    @Test
    public void zenTaoLogout(){
        given().log().all()
                .cookie("lang", "zh-cn").cookie(" device", "desktop")
                .cookie(" theme", "default").cookie(" bugModule", "0")
                .cookie(" preBranch", "0").cookie(" qaBugOrder", "id_desc")
                .cookie(" ajax_quickJump", "on").cookie(" lastProject", "969")
                .cookie(" preProjectID", "969").cookie(" moduleBrowseParam", "0")
                .cookie(" productBrowseParam", "0").cookie(" projectTaskOrder", "status%2Cid_desc")
                .cookie(" preProductID", "116").cookie(" lastProduct", "116")
                .cookie(" windowWidth", "1255").cookie(" windowHeight", "803")
                .cookie(" Hm_lvt_11b88b9faf7b3a70c7ad4d0917b9165a", "1532333631")
                .cookie(" zentaosid", "rk6pqast1spsoukm3kfksvg3s1")
                .when().get("http://www.thinkive.cn:10000/zentao/user-logout.html")
                .then().statusCode(200);
    }

    @Ignore("Not Ready to Run")
    @Test
    public void zentaoSeeBUG(){
        given().log().all()
                .cookie("lang", "zh-cn").cookie(" device", "desktop")
                .cookie(" theme", "default").cookie(" bugModule", "0")
                .cookie(" preBranch", "0").cookie(" qaBugOrder", "id_desc")
                .cookie(" ajax_quickJump", "on").cookie(" lastProject", "969")
                .cookie(" preProjectID", "969").cookie(" moduleBrowseParam", "0")
                .cookie(" productBrowseParam", "0").cookie(" projectTaskOrder", "status%2Cid_desc")
                .cookie(" preProductID", "116").cookie(" lastProduct", "116")
                .cookie(" windowWidth", "1255").cookie(" windowHeight", "803")
                .cookie(" Hm_lvt_11b88b9faf7b3a70c7ad4d0917b9165a", "1532333631")
                .cookie(" zentaosid", "rk6pqast1spsoukm3kfksvg3s1")
                .when().get("http://www.thinkive.cn:10000/zentao/bug-view-141702.html")
                .then().statusCode(302).log().all();
    }

}
