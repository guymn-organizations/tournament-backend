package tour.rov.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.PlayerPost;
import tour.rov.profile.service.PlayerPostService;

@RestController
@RequestMapping("player_post")
public class PlayerPostController {
    @Autowired
    private PlayerPostService playerPostService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PlayerPost playerPost) {
        try {
            playerPostService.savePlayerPost(playerPost);
            return ResponseEntity.status(HttpStatus.CREATED).body("Profile was created\n" + playerPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create post : " + e.getMessage());
        }
    }
}
