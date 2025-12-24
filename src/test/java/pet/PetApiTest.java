package pet;
import base.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import model.Pet;

public class PetApiTest extends TestBase {

    @Test
    void createAndGetPet(){
        long petId = ThreadLocalRandom.current().nextLong(1_000_000);

        Pet pet = new Pet();
        pet.id = petId;
        pet.name = "PetTest1";
        pet.status = "available";
        pet.photoUrls = Collections.singletonList("https://example.com/photo.jpg");

        given()
                .contentType(ContentType.JSON)
                .body(pet)
        .when()
                .post("/pet")
        .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("id", equalTo((int) petId))
                .body("name", equalTo("PetTest1"))
                .body("status", equalTo("available"));

        given()
                .pathParam("petId", petId)
        .when()
                .get("/pet/{petId}")
        .then()
                .statusCode(200)
                .body("id", equalTo((int) petId))
                .body("name", equalTo("PetTest1"))
                .body("status", equalTo("available"));
    }
}
