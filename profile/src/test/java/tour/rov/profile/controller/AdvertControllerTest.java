package tour.rov.profile.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Advert;
import tour.rov.profile.service.AdvertService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AdvertControllerTest {

    @InjectMocks
    private AdvertController advertController;

    @Mock
    private AdvertService advertService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAdvert() {
        // Create example data for testing
        List<Advert> exampleAdverts = new ArrayList<>();
        exampleAdverts.add(new Advert("exampleImageUrl1", "exampleLinkUrl1"));
        exampleAdverts.add(new Advert("exampleImageUrl2", "exampleLinkUrl2"));

        // Mock the behavior of the AdvertService to return the example data
        Mockito.when(advertService.getAllAdverts(Mockito.anyInt(), Mockito.anyInt())).thenReturn(exampleAdverts);

        // Perform the GET request
        ResponseEntity<?> response = advertController.getAllAdvert(0, 10);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        List<Advert> retrievedAdverts = (List<Advert>) response.getBody();
        assert retrievedAdverts != null;
        assert retrievedAdverts.size() == 2;  // Check the number of adverts returned
    }

    @Test
    public void testCreateAdvert() {
        // Create an example Advert for testing
        Advert exampleAdvert = new Advert("exampleImageUrl", "exampleLinkUrl");

        // Mock the behavior of the AdvertService to simulate the save operation
        Mockito.doNothing().when(advertService).saveAdvert(exampleAdvert);

        // Perform the POST request
        ResponseEntity<?> response = advertController.createAdvert(exampleAdvert);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.CREATED);
        assert response.getBody().toString().contains("Advertisement was created");
    }
}
