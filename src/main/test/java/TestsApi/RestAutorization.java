package TestsApi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAutorization {
    private static final String BASE_URL = "https://reqres.in/api";
    private static RequestSpecification REQUEST_SPECIFICATION;
    private Cookies cookies;

    public UserServices user;
    public OrserService order;

    private RestAutorization(Cookies cookies){
        this.cookies = cookies;
        user = new UserServices(cookies);
        order = new OrserService(cookies);

       }

    public static RestAutorization loginAs(String login, String password){
        Cookies cookies = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("/login")
                .body(new UserLogin(login, password))
                .post()
                .detailedCookies();
        //in the end return object with init cookies
        return new RestAutorization(cookies);
    }

    public List<DeserializaFullTest> getUsers(){
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", DeserializaFullTest.class);
    }
    public CreateUserResponseTest createUser(CreateUserTest createUserTest){
        return given().body(createUserTest).post().as(CreateUserResponseTest.class);

    }

}
