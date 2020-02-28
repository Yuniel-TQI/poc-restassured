import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;
import org.json.simple.JSONObject;


public class Teste {

    @Test
    public void  testGet()
    {
       String email =  given().
               param("page","2").
               param("per_page","6").
               param("id","7").
               param("email","michael.lawson@reqres.in").
               param("first_name","Michael").

                when().
                get( "https://reqres.in/api/users").
                then().
                assertThat()
                    .statusCode(200)
                    .extract()
                        .body()
                        .path("data.email");
        Assert.assertEquals("michael.lawson@reqres.in", email);
    }

    @Test
    public void  testPost()
    {
      String nome =  given().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").
                when().
                post("https://reqres.in/api/users").
                then()
                .assertThat()
                .statusCode(201)
                    .extract()
                    .body()
                    .path("name");
            Assert.assertEquals("morpheus", nome);
    }

    @Test
    public void  testPost1()
    {
        String email =  given().
                body("\"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"").
                when().
                post("https://reqres.in/api/users").
                then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .path("email");
        Assert.assertEquals("eve.holt@reqres.in", email);
    }

    @Test
    public void  testPost2()
    {
        String nome =  given().
                body("{\"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\",\n" +
                        "    \"id\": \"206\",\n" +
                        "    \"createdAt\": \"2020-02-26T18:16:06.906Z\"}").
                when().
                post("https://reqres.in/api/users").
                then()
                .assertThat()
                .statusCode(201)
                .extract()
                .body()
                .path("createdAt");
        Assert.assertEquals("createdAt", "2020-02-26T18:16:06.906Z");
    }

    @Test
    public void  testPost3()
    {
       given().
                body("{\"id\": 4,\n" +
                        "    \"token\": \"QpwL5tke4Pnpja7X4\"}").
                when().
                post("https://reqres.in/api/register/2").
                then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    public void postUserTest(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Rafael");
        requestParams.put("job", "QA");

        given().
                body(requestParams.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).
                body(containsString("createdAt"));
    }

    @Test
    public void putUserTest(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Rafael A.");
        requestParams.put("job", "QA/DEV");

        given().
                body(requestParams.toJSONString()).
                when().
                put("https://reqres.in/api/users" + "/2").
                then().
                statusCode(200).
                body(containsString("updatedAt"));
    }

    @Test
    public void deleteUserTest(){
        when().
                delete("https://reqres.in/api/users" + "/2").
                then()
                .statusCode(204);
    }

    @Test
    public void getPageOneTest(){
        given().
                param("page", "1").
                when().
                get("https://reqres.in/api/users").
                then().
                statusCode(200).
                body("page", equalTo(1));
    }

    @Test
    public void getUserTest() {
        when().
                get("https://reqres.in/api/users" + "/2").then().body("data.id", equalTo(2));
    }

    @Test
    public void  testPut()
    {
        String nome = given().
                body("{\"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\",\n" +
                        "    \"updatedAt\": \"2020-02-27T11:56:42.254Z\"}").
                when().
                put("https://reqres.in/api/users/2").
                then()
                .assertThat()
                .statusCode(200)
            .extract()
            .body()
            .path("updatedAt");
        Assert.assertEquals("2020-02-27T11:56:42.254Z", nome);
    }

    @Test
    public void  testGet1()
    {
        String email =  given().
                param("id","2").
                param("email","janet.weaver@reqres.in").
                param("first_name","Janet").
                param("last_name","Weaver").
                param("avatar","https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg").

                when().
                get( "https://reqres.in/api/users/2").
                then().
                assertThat()
                .statusCode(200)
                .extract()
                .body()
                .path("data.email");
        Assert.assertEquals("janet.weaver@reqres.in", email);
    }

    @Test
    public void  testGet2()
    {
        given().
                param("id","2").
                param("email","janet.weaver@reqres.in").
                param("first_name","Janet").
                param("last_name","Weaver").
                param("avatar","https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg").

                when().
                get( "https://reqres.in/api/users/2").

                then().
                statusCode(200).
                body("data.id", equalTo(2)).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet"));
    }

}
