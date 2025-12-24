package base;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="/v2";
    }
}
