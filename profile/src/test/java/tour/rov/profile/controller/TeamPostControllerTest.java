package tour.rov.profile.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.TeamPost;
import tour.rov.profile.service.TeamPostService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamPostControllerTest {

    @InjectMocks
    private TeamPostController teamPostController;

    @Mock
    private TeamPostService teamPostService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTeamPost() {
        // TeamPost teamPost = new TeamPost();
        // Mockito.when(teamPostService.saveTeamPost(Mockito.any(TeamPost.class)))
        //     .thenReturn(teamPost);

        // ResponseEntity<?> response = teamPostController.createPost(teamPost);

        // assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // assertEquals(teamPost, response.getBody());
    }

    @Test
    public void testGetAllTeamPosts() {
        int pageIndex = 0;
        int pageSize = 10;

        List<TeamPost> teamPosts = new ArrayList<>();
        Mockito.when(teamPostService.getAllTeamPosts(pageIndex, pageSize))
            .thenReturn(teamPosts);

        ResponseEntity<?> response = teamPostController.getAllTeamPost(pageIndex, pageSize);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teamPosts, response.getBody());
    }

    @Test
    public void testEditTeamPost() {
        // String postId = "post123";
        // TeamPost teamPost = new TeamPost();
        // Mockito.when(teamPostService.updateTeamPost(postId, teamPost))
        //     .thenReturn(teamPost);

        // ResponseEntity<?> response = teamPostController.editPost(postId, teamPost);

        // assertEquals(HttpStatus.OK, response.getStatusCode());
        // assertEquals("Post was updated", response.getBody());
    }
}

