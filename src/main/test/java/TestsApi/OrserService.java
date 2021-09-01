package TestsApi;

import io.restassured.http.Cookies;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrserService extends RestService {
    @Override
    protected String getBasePath() {
        return "/orders";
    }

    public OrserService(Cookies cookies) {
        super(cookies);
    }

    public List<DeserializaFullTest> getUsers(){
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", DeserializaFullTest.class);
    }
}
