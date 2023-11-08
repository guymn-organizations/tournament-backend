package tour.rov.profile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.PlayerPost;

@SpringBootTest
public class PlayerPostControllerTest {

    @Autowired
    private PlayerPostController playerPostController;

    @Test
    public void testCreatePost() {
        PlayerPost playerPost = new PlayerPost(); // Create a PlayerPost object with test data

        ResponseEntity<?> response = playerPostController.createPost(playerPost);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetAllPlayerPost() {
        ResponseEntity<?> response = playerPostController.getAllPlayerPost();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
