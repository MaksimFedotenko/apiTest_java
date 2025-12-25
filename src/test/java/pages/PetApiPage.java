package pages;

import io.restassured.http.ContentType;
import model.Pet;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetApiPage {

    public Pet createPet(Pet pet){
        return given()
                .contentType(ContentType.JSON)
                .body(pet)
        .when()
                .post("/pet")
        .then()
                .statusCode(200)
                .body("name", equalTo(pet.name))
                .extract().as(Pet.class);
    }

    public Pet getPet(long petId){
        return given()
                .pathParam("petId", petId)
        .when()
                .get("/pet/{petId}")
        .then()
                .statusCode(200)
                .extract().as(Pet.class);
    }

    public Pet updatePet(Pet pet){
        return given()
                .contentType(ContentType.JSON)
                .body(pet)
        .when()
                .put("/pet")
        .then()
                .statusCode(200)
                .body("name", equalTo(pet.name))
                .body("photoUrls", equalTo(pet.photoUrls))
                .extract().as(Pet.class);
    }

    public void deletePet(long petId){
        given()
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}")
        .then()
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo("" + petId));
    }
}
