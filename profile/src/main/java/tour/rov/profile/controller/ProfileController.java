package tour.rov.profile.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Message;
import tour.rov.profile.model.Profile;
import tour.rov.profile.model.ProfileGame;
import tour.rov.profile.service.ProfileService;

@RestController
@RequestMapping("profiles")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Profile profile) {
        try {
            profile.setMessages(new ArrayList<Message>());
            profile.setProfileGame(null);
            profile.getProfileGame().setMyTeam(null);

            profileService.saveProfile(profile);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exist email");
        }
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {

        Profile profile = profileService.getProfileByEmail(email);

        if (profile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worng email");

        } else if (!password.equals(profile.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Worng password");

        }
        return ResponseEntity.ok(profile.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPrifileById(@PathVariable String id) {

        try {
            if (profileService.existingProfile(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("please login");
            }
            Profile profile = profileService.findById(id);

            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to login : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProfile(@PathVariable String id, @RequestBody Profile updatedProfile) {

        try {
            if (profileService.existingProfile(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
            }

            profileService.updateProfile(id, updatedProfile);
            return ResponseEntity.ok().body("Profile was updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to edit profile : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/set_profile_game")
    public ResponseEntity<?> setProfileGame(@PathVariable String id, @RequestBody ProfileGame profileGame) {
        try {
            if (profileService.existingProfile(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
            }

            profileService.updateProfileGame(id, profileGame);
            return ResponseEntity.ok().body("ProfileGame was updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to edit game profile : " + e.getMessage());
        }
    }

    @GetMapping("/{id}/message")
    public ResponseEntity<?> getMessages(@PathVariable String id) {
        try {
            if (profileService.existingProfile(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
            }

            List<Message> messages = profileService.getMessages(id);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get message : " + e.getMessage());
        }
    }
}