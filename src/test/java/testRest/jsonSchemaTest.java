package testRest;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.matchesDtd;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;

public class jsonSchemaTest {

    @Test
    public void douban1() {
        //通过数组对比，来断言得到的结果
        Response RE =
                when().get("https://api.douban.com/v2/book/user/hecaitou/tags")
                        .then().log().all()
                        .body("tags.count.size()", greaterThan(6))
                        .extract().response();

        String s1 = RE.jsonPath().getString("tags.count[1]");
        System.out.println(s1);

        List list = new ArrayList();
        list.add("冯唐");
        list.add("亨利.米勒");
        list.add("黑塞");

        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(list.get(i), RE.jsonPath().getString("tags.name[" + i + "]"));
        }
    }


    @Test
    public void jsonchema() {
        useRelaxedHTTPSValidation();
        //json-schema判断结构
        //https://jsonschema.net  自动转换json-schema文件
        Response response = get("https://api.douban.com/v2/book/user/hecaitou/tags");
        //test.json文件一般放在资源目录中
        response.then().assertThat().body(matchesJsonSchemaInClasspath("test.json"));

//        //rest-assured 的 json-schema-validator module 使用的是 Francis Galiegue's  json-schema-validator(fge) 库来实现验证(Validation)。如果想要配置基础 fge 库，我们可以这样写
//        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();
//        //这个 using 方法允许我们传递一个jsonSchemaFactory 实例
//        get("/products").then().assertThat().body(matchesJsonSchemaInClasspath("products-schema.json").using(jsonSchemaFactory));
    }

    @Test
    public void xmlschema(){
        get("/carRecords").then().assertThat().body(matchesXsd("xxx.xsd"));

        get("/videos").then().assertThat().body(matchesDtd("xxx.dtd"));
    }

}
