package tour.rov.profile.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.TeamPost;
import tour.rov.profile.model.Position;
import tour.rov.profile.service.TeamPostService;

@RestController
@RequestMapping("team_post")
public class TeamPostController {
    @Autowired
    private TeamPostService TeamPostService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody TeamPost TeamPost) {
        try {
            TeamPost.setPositions(new ArrayList<Position>());
            TeamPostService.saveTeamPost(TeamPost);
            return ResponseEntity.status(HttpStatus.CREATED).body("Post was created\n" + TeamPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create post : " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTeamPost() {
        try {
            List<TeamPost> teamPosts = TeamPostService.getAllTeamPosts();
            return ResponseEntity.ok(teamPosts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get posts : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPost(@PathVariable String id, @RequestBody TeamPost TeamPost) {
        try {
            TeamPostService.updateTeamPost(id, TeamPost);
            return ResponseEntity.ok().body("Post was updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get posts : " + e.getMessage());
        }
    }
}