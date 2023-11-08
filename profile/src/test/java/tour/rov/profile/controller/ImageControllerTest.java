package tour.rov.profile.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Image;
import tour.rov.profile.service.ImageService;

public class ImageControllerTest {

    @InjectMocks
    private ImageController imageController;

    @Mock
    private ImageService imageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateImage_Success() {
        // Create an example Image object
        Image exampleImage = new Image("1", "exampleImageUrl");

        // Mock the behavior of the ImageService to simulate a successful operation
        Mockito.doNothing().when(imageService).saveImage(exampleImage);

        // Perform the POST request
        ResponseEntity<?> response = imageController.createImage(exampleImage);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody().equals("1");
    }

    @Test
    public void testCreateImage_NoImageUrl() {
        // Create an example Image object with no image URL
        Image exampleImage = new Image("2", null);

        // Perform the POST request
        ResponseEntity<?> response = imageController.createImage(exampleImage);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE);
        assert response.getBody().equals("Must have image");
    }

    @Test
    public void testGetImageById_Success() {
        // Create an example Image object
        Image exampleImage = new Image("3", "exampleImageUrl");

        // Mock the behavior of the ImageService to simulate a successful operation
        Mockito.when(imageService.getImageById("3")).thenReturn(exampleImage);

        // Perform the GET request
        ResponseEntity<?> response = imageController.getImageById("3");

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody().equals("exampleImageUrl");
    }

    @Test
    public void testGetImageById_Failure() {
        // Mock the behavior of the ImageService to simulate an exception
        Mockito.doThrow(new RuntimeException("Simulated error")).when(imageService).getImageById("4");

        // Perform the GET request
        ResponseEntity<?> response = imageController.getImageById("4");

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
        assert response.getBody().toString().contains("Failed to create image");
    }

    @Test
    public void testDeleteImageById_Success() {
        // Mock the behavior of the ImageService to simulate a successful operation
        Mockito.doNothing().when(imageService).deleteById("5");

        // Perform the DELETE request
        ResponseEntity<?> response = imageController.deleteById("5");

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody().equals("Deleted");
    }

    @Test
    public void testDeleteImageById_Failure() {
        // Mock the behavior of the ImageService to simulate an exception
        Mockito.doThrow(new RuntimeException("Simulated error")).when(imageService).deleteById("6");

        // Perform the DELETE request
        ResponseEntity<?> response = imageController.deleteById("6");

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
        assert response.getBody().toString().contains("Failed to delete image");
    }
}
