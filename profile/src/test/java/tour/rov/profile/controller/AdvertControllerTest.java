package tour.rov.profile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tour.rov.profile.model.Advert;

@SpringBootTest
public class AdvertControllerTest {

    @Autowired
    private AdvertController advertController;

    @Test
    public void testCreateAdvert() {
        Advert advert = new Advert(); // Create an Advert object with test data

        ResponseEntity<?> response = advertController.createAdvert(advert);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
