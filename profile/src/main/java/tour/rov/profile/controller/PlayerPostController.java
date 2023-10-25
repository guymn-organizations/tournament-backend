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

import tour.rov.profile.model.PlayerPost;
import tour.rov.profile.model.Position;
import tour.rov.profile.service.PlayerPostService;

@RestController
@RequestMapping("player_post")
public class PlayerPostController {
    @Autowired
    private PlayerPostService playerPostService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PlayerPost playerPost) {
        try {
            playerPost.setPositions(new ArrayList<Position>());
            playerPostService.savePlayerPost(playerPost);
            return ResponseEntity.status(HttpStatus.CREATED).body("Profile was created\n" + playerPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create post : " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayerPost() {
        try {
            List<PlayerPost> playerPosts = playerPostService.getAllPlayerPosts();
            return ResponseEntity.ok(playerPosts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get posts : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPost(@PathVariable String id, @RequestBody PlayerPost playerPost) {
        try {
            playerPostService.updatePlayerPost(id, playerPost);
            return ResponseEntity.ok().body("Post was updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get posts : " + e.getMessage());
        }
    }

}
