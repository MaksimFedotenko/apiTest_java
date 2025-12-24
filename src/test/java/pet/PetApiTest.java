package pet;

import base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import pages.PetApiPage;
import model.Pet;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(AllureJunit5.class)
public class PetApiTest extends TestBase {
    private final PetApiPage petApi = new PetApiPage();

    @Test
    @Description("CRUD api test")
    void CRUDTest() {
        long petId = ThreadLocalRandom.current().nextLong(1, 10000); // Безопасный диапазон

        Pet pet = new Pet();
        pet.id = petId;
        pet.name = "PetTest1";
        pet.status = "available";
        pet.photoUrls = Collections.singletonList("https://example.com/photo.jpg");

        // Post
        Pet created = petApi.createPet(pet);
        // Get
        Pet fetched = petApi.getPet(created.id);
        // Put, обновили имя и photoUrls
        fetched.name = "PetTestUpdate";
        fetched.photoUrls = Collections.singletonList("https://example.com/photo2.jpg");
        Pet updated = petApi.updatePet(fetched);
        //Get после Put
        Pet featchedAfterUpdate = petApi.getPet(updated.id);
        // Удаление
        petApi.deletePet(updated.id);

        assertEquals("PetTest1", created.name);
        assertEquals(created.id, fetched.id);
        assertEquals("PetTestUpdate", updated.name);
        assertEquals("https://example.com/photo2.jpg", updated.photoUrls.get(0));
        assertEquals(updated.name, featchedAfterUpdate.name);
        assertEquals(updated.photoUrls, featchedAfterUpdate.photoUrls);
    }
}
