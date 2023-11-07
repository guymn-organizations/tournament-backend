package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.TeamPost;
import tour.rov.profile.service.TeamPostService;

@RestController
@RequestMapping("team_post")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class TeamPostController {
    @Autowired
    private TeamPostService teamPostService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody TeamPost teamPost) {
        try {
            teamPostService.saveTeamPost(teamPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamPostService.findById(teamPost.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create post : " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTeamPost(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "10") int pageSize) {
        try {
            List<TeamPost> teamPosts = teamPostService.getAllTeamPosts(pageIndex, pageSize);
            return ResponseEntity.ok(teamPosts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get posts : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPost(@PathVariable String id, @RequestBody TeamPost TeamPost) {
        try {
            teamPostService.updateTeamPost(id, TeamPost);
            return ResponseEntity.ok().body("Post was updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get posts : " + e.getMessage());
        }
    }
}
