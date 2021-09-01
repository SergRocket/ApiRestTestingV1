package TestsApi;

import Steps.UserStep;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {
    private static RestAutorization api;

    @BeforeClass
    public static void prepeareClient(){
        api = RestAutorization.loginAs("eve.holt@reques.in", "citysLicka");
    }

    //for eliminating code repeats
    private static final RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/api")
            .setBasePath("/users")
            .setContentType(ContentType.JSON)
            .build();

    @Test
    public void getUsers(){
        given()
                .spec(REQUEST_SPECIFICATION)
                .when().get()
                .then().statusCode(200);
    }
    @Test
    public void getOneUsersName(){
        given()
                .spec(REQUEST_SPECIFICATION)
                .when().get()
                .then()
                .statusCode(200)
                .body("data[0].email", equalTo("george.bluth@reqres.in"));

       }

       @Test
    public void printUserName(){
        String user = given().spec(REQUEST_SPECIFICATION)
                .when().get()
                .then()
                .statusCode(200)
                .extract().asString();
        System.out.println(user);
       }

       @Test
    public void checkUserNameWithLamda (){
        given().spec(REQUEST_SPECIFICATION)
                .when().get()
                .then()
                .statusCode(200)
                .body("data.find{it.email=='george.bluth@reqres.in'}.first_name", equalTo("George"));
       }
       @Test
    public void getTheListOfEmails(){
        List<String> emails = given()
                .spec(REQUEST_SPECIFICATION)
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
                .spec(REQUEST_SPECIFICATION)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data", DeserializaTest.class);
                assertThat(users).extracting(DeserializaTest::getEmail).contains("george.bluth@reqres.in");
       }
      @Test
    public void deserializationOfTheListtoOBJFULL() {

        assertThat(api.getUsers()).extracting(DeserializaFullTest::getEmail).contains("george.bluth@reqres.in");
    }

    @Test
    public void createUser(){


        CreateUserTest createUsersTest = UserGenerator.getSimpleUser();
        CreateUserResponseTest createUserResponseTest = api.createUser(createUsersTest);

        /*CreateUserResponseTest createUserResponseTest = given(createUserTest)
                .spec(REQUEST_SPECIFICATION)
                .body(createUserTest)
                .when().post()
                //json can be changed into Object with crUserRespTest
                .then().extract().as(CreateUserResponseTest.class);*/
        //checking that created user is the same as we wanted
        assertThat(createUserResponseTest)
                .isNotNull()
                .extracting(CreateUserResponseTest::getName)
                .isEqualTo(createUsersTest.getName());

        assertThat(createUsersTest)
                .isNotNull()
                .extracting(CreateUserTest::getName)
                .isEqualTo(createUsersTest.getName());
    }

}


