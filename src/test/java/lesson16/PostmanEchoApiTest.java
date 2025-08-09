package lesson16;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class PostmanEchoApiTest {
    private RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
        // Создаем спецификацию без автоматического charset
        requestSpec = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Cookie", "sails.sid=s%3Abfu50sNTov6I0riKBeSWEotBnpD1H3Kd.uPvD%2FyGYm4XtfvAyq6wv80x7rA5ckORgnFj30X9qFDE")
                .build();
    }

    @Test
    public void testGetRequest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("foo1", "bar1");
        queryParams.put("foo2", "bar2");

        Response response = given()
                .header("Cookie", "sails.sid=s%3AhjGFAAVidvDlRTEgd-cFvlHou4jsXmW4.V7xGo%2BgvCXKdnTbVG2ig%2BbE5TscUnGl35iBvIrWUChk")
                .queryParams(queryParams)
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .extract()
                .response();

        // Дополнительная проверка тела ответа
        Map<String, String> args = response.jsonPath().getMap("args");
        assertEquals(args.get("foo1"), "bar1", "Query param foo1 should match");
        assertEquals(args.get("foo2"), "bar2", "Query param foo2 should match");
    }

    @Test
    public void testPostRequestJson() {
        String requestBody = "{\"test\": \"value\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "sails.sid=s%3AT4aoNMXJFrJ7Ue7CMKvy4WUOfkcgP295.8ekMMVNpRYfY1uXWc7LsEoLsWXxa4ZlGA0b3%2B0Tffew")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data.test", equalTo("value"))
                .body("json.test", equalTo("value"))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("headers.host", equalTo("postman-echo.com"))
                .extract()
                .response();

        // Дополнительная проверка тела ответа
        Map<String, Object> json = response.jsonPath().getMap("json");
        assertEquals(json.get("test"), "value", "JSON body should match");
    }

    @Test
    public void testPostFormUrlEncodedWithCookieFullBodyCheck() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("foo1", "bar1");
        formParams.put("foo2", "bar2");

        given()
                .spec(requestSpec)
                .formParams(formParams)
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(200)
                // Проверка пустых объектов args, data, files
                .body("args", anEmptyMap())
                .body("data", isEmptyString())
                .body("files", anEmptyMap())
                // Проверка формы
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                // Проверка заголовков
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/x-www-form-urlencoded; charset=UTF-8"))
                .body("headers.cookie", containsString("sails.sid="))
                // Проверка json
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"))
                // Проверка URL
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    public void testPutRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType(ContentType.TEXT)
                .header("Cookie", "sails.sid=s%3A9CtC5MmJd_lz2K2ruU6cm_gxe1i7Ffey.6wKpqrZxVFqn7ZIED1a4oadMM9V7F%2FHxHPMG5JUieys")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/put"))
                .body("headers.host", equalTo("postman-echo.com"))
                .extract()
                .response();

        // Дополнительная проверка тела ответа
        String data = response.jsonPath().getString("data");
        assertEquals(data, requestBody, "Response data should match request body");
    }

    @Test
    public void testPatchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType(ContentType.TEXT)
                .header("Cookie", "sails.sid=s%3AXCAWiSf6sAODNXY-bYnTA-3bXwPdn2h9.5kzPHn7d5iH0pKXH%2FCjFbQinM%2Fg7JWEM2PILd5X5pDs")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/patch"))
                .body("headers.host", equalTo("postman-echo.com"))
                .extract()
                .response();

        // Дополнительная проверка тела ответа
        String data = response.jsonPath().getString("data");
        assertEquals(data, requestBody, "Response data should match request body");
    }

    @Test
    public void testDeleteRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType(ContentType.TEXT)
                .header("Cookie", "sails.sid=s%3AXCAWiSf6sAODNXY-bYnTA-3bXwPdn2h9.5kzPHn7d5iH0pKXH%2FCjFbQinM%2Fg7JWEM2PILd5X5pDs")
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/delete"))
                .body("headers.host", equalTo("postman-echo.com"))
                .extract()
                .response();

        // Дополнительная проверка тела ответа
        String data = response.jsonPath().getString("data");
        assertEquals(data, requestBody, "Response data should match request body");
    }
}