import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BaseTest {
    public static void main (String[] args){
        RestAssured.baseURI="https://maps.googleapis.com";
        given().
                param("query","restaurants+in+Sydney").
                param("key","AIzaSyB4WBt4eRefXsQ83Azcbv8BGRO-iHIfwbc").
                header("key","AIzaSyB4WBt4eRefXsQ83Azcbv8BGRO-iHIfwbc");
                when().
                        get("/maps/api/place/textsearch/xml").
                        then().assertThat().
                        statusCode(200);
         given().
                 param("query","123+main+street").
                 param("key","AIzaSyB4WBt4eRefXsQ83Azcbv8BGRO-iHIfwbc").
                 header("key","AIzaSyB4WBt4eRefXsQ83Azcbv8BGRO-iHIfwbc");
                 when().
                         get("/maps/api/place/textsearch/json").
                         then().assertThat().statusCode(200);
                 System.out.print("The GET requests were OK");

        }
}
