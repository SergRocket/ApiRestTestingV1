package TestsApi;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {
    @Test
    public void getUsers(){
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200);
    }
@Test
public void getOneUsersName(){
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("data[0].email", equalTo("george.bluth@reqres.in"));

       }

       @Test
    public void printUserName(){
        String user = given().baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().asString();
        System.out.println(user);
       }

       @Test
    public void checkUserNameWithLamda (){
        given().baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("data.find{it.email=='george.bluth@reqres.in'}.first_name", equalTo("George"));
       }
       @Test
    public void getTheListOfEmails(){
        List<String> emails = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data.email");
                 System.out.println(emails.get(2));
                 System.out.println(emails.get(3));
                 System.out.println(emails.get(4));
       }

       @Test
    public void deserializationOfTheListtoOBJ(){
         List<DeserializaTest> users = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data", DeserializaTest.class);
                assertThat(users).extracting(DeserializaTest::getEmail).contains("george.bluth@reqres.in");
       }
      @Test
    public void deserializationOfTheListtoOBJFULL() {
        List<DeserializaFullTest> users = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data", DeserializaFullTest.class);
        assertThat(users).extracting(DeserializaFullTest::getEmail).contains("george.bluth@reqres.in");
    }

    @Test
    public void createUser(){
        CreateUserTest createUserTest = new CreateUserTest();
        createUserTest.setName("Serg");
        createUserTest.setPosition("Senior_QAA");

        CreateUserResponseTest createUserResponseTest = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .body(createUserTest)
                .when().post()
                //json can be changed into Object with crUserRespTest
                .then().extract().as(CreateUserResponseTest.class);
        //checking that created user is the same as we wanted
        assertThat(createUserResponseTest)
                .isNotNull()
                .extracting(CreateUserResponseTest::getName)
                .isEqualTo(createUserTest.getName());
    }
}


