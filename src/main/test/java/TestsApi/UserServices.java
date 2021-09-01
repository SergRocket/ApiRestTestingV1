package TestsApi;

import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;

import java.util.List;


import static io.restassured.RestAssured.given;

public class UserServices extends RestService {
    @Override
    protected String getBasePath() {
        return "/users";
    }

    public UserServices(Cookies cookies) {
        super(cookies);
    }

    public CreateUserResponseTest reateUser(CreateUserTest createUserTest){
        return given().spec(REQUEST_SPECIFICATION).body(createUserTest).post().as(CreateUserResponseTest.class);
    }

    public List<DeserializaFullTest> gerUsers(){
        return given().spec(REQUEST_SPECIFICATION)
               .get().jsonPath().getList("data", DeserializaFullTest.class);
    }
}
