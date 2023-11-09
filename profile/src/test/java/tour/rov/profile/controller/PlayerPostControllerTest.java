package tour.rov.profile.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tour.rov.profile.model.PlayerPost;
import tour.rov.profile.service.PlayerPostService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayerPostControllerTest {

    @InjectMocks
    private PlayerPostController playerPostController;

    @Mock
    private PlayerPostService playerPostService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePost_Success() {
        // Create an example player post
        PlayerPost examplePlayerPost = new PlayerPost();

        // Mock the behavior of the PlayerPostService to simulate a successful post creation
        Mockito.doAnswer(invocation -> {
            PlayerPost playerPost = invocation.getArgument(0);
            return playerPost;
        }).when(playerPostService).savePlayerPost(examplePlayerPost);

        // Perform the POST request to create a player post
        ResponseEntity<?> response = playerPostController.createPost(examplePlayerPost);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.CREATED);
        assert response.getBody() != null;
        assert response.getBody() instanceof PlayerPost;
    }

    @Test
    public void testCreatePost_Failure() {
        // Create an example player post
        PlayerPost examplePlayerPost = new PlayerPost();

        // Mock the behavior of the PlayerPostService to simulate an exception during post creation
        Mockito.doThrow(new RuntimeException("Simulated error")).when(playerPostService).savePlayerPost(examplePlayerPost);

        // Perform the POST request to create a player post
        ResponseEntity<?> response = playerPostController.createPost(examplePlayerPost);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testGetAllPlayerPost_Success() {
        // Create a list of example player posts
        List<PlayerPost> examplePlayerPosts = new ArrayList<>();
        examplePlayerPosts.add(new PlayerPost());
        examplePlayerPosts.add(new PlayerPost());

        // Mock the behavior of the PlayerPostService to simulate successful retrieval
        Mockito.when(playerPostService.getAllPlayerPosts(0, 10)).thenReturn(examplePlayerPosts);

        // Perform the GET request to retrieve all player posts
        ResponseEntity<?> response = playerPostController.getAllPlayerPost(0, 10);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody() != null;
        assert response.getBody() instanceof List;
        assert ((List<?>) response.getBody()).size() == 2;
    }

    @Test
    public void testEditPost_Success() {
        // Create an example player post
        PlayerPost examplePlayerPost = new PlayerPost();

        // Mock the behavior of the PlayerPostService to simulate a successful update
        Mockito.doAnswer(invocation -> null).when(playerPostService).updatePlayerPost("1", examplePlayerPost);

        // Perform the PUT request to edit a player post
        ResponseEntity<?> response = playerPostController.editPost("1", examplePlayerPost);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testEditPost_Failure() {
        // Create an example player post
        PlayerPost examplePlayerPost = new PlayerPost();

        // Mock the behavior of the PlayerPostService to simulate an exception during the update
        Mockito.doThrow(new RuntimeException("Simulated error")).when(playerPostService).updatePlayerPost("2", examplePlayerPost);

        // Perform the PUT request to edit a player post
        ResponseEntity<?> response = playerPostController.editPost("2", examplePlayerPost);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Additional test methods for other controller methods can be added similarly.
}
