package Steps;

import TestsApi.CreateUserResponseTest;
import TestsApi.CreateUserTest;
import TestsApi.DeserializaFullTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserStep {

    //for eliminating code repeats
    private static final RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/api")
            .setBasePath("/users")
            .setContentType(ContentType.JSON)
            .build();

    public static List<DeserializaFullTest> getUsers(){
        return given().spec(REQUEST_SPECIFICATION)
               .get()
               .jsonPath().getList("data", DeserializaFullTest.class);
    }
    private CreateUserResponseTest user;
    public CreateUserResponseTest createUser(CreateUserTest createUserTest){
        user = given().body(createUserTest).post().as(CreateUserResponseTest.class);
        return user;
    }

    //get the last created user data
    public DeserializaFullTest getLastUser(){
        return given().get("/" + user.getId()).as(DeserializaFullTest.class);
    }

    public static DeserializaFullTest getLastUser(int id){
        return given().get("/" + id).as(DeserializaFullTest.class);
    }

}
