import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


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
                body("data.email", equalTo("janet.weaver@reqres.in"));


    }
}
