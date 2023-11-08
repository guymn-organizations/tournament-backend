package tour.rov.profile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.TeamPost;

@SpringBootTest
public class TeamPostControllerTest {

    @Autowired
    private TeamPostController teamPostController;

    @Test
    public void testCreatePost() {
        TeamPost teamPost = new TeamPost(); // Create a TeamPost object with test data

        ResponseEntity<?> response = teamPostController.createPost(teamPost);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetAllTeamPost() {
        ResponseEntity<?> response = teamPostController.getAllTeamPost();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
