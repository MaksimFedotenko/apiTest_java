package pet;

import base.TestBase;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import model.Pet;

@ExtendWith(AllureJunit5.class)
public class PetApiTest extends TestBase {

    @Test
    @Description("Create and get pet test")
    void createAndGetPet() {
        long petId = ThreadLocalRandom.current().nextLong(1, 10000); // Безопасный диапазон

        Pet pet = new Pet();
        pet.id = petId;
        pet.name = "PetTest1";
        pet.status = "available";
        pet.photoUrls = Collections.singletonList("https://example.com/photo.jpg");

        // Создание
        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo((int) petId))
                .body("name", equalTo("PetTest1"))
                .body("status", equalTo("available"));

        // Получение
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
