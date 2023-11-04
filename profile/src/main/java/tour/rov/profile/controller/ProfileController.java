package tour.rov.profile.controller;

import java.util.ArrayList;

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

            if (profileService.existingProfileByEmail(profile.getEmail())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Email is already use");
            }

            profile.setMessages(new ArrayList<String>());
            profile.setProfileGame(null);
            profile.setImageProfileUrl(null);

            profileService.saveProfile(profile);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to register : " + e.getMessage());
        }
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
        try {
            Profile profile = profileService.getProfileByEmail(email);

            if (profile == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worng email");

            } else if (!password.equals(profile.getPassword())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Worng password");

            }
            return ResponseEntity.ok(profile.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to login : " + e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPrifileById(@PathVariable String id) {

        if (profileService.existingProfile(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notfound profile");
        }
        Profile profile = profileService.findById(id);

        return ResponseEntity.ok(profile);

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getPrifileByName(@PathVariable String name) {
        Profile profile = profileService.getProfileByProfilegameName(name);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProfile(@PathVariable String id, @RequestBody Profile updatedProfile) {

        try {
            if (profileService.existingProfile(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
            }

            profileService.updateProfile(id, updatedProfile);
            return ResponseEntity.ok().body(profileService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to edit profile : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/game")
    public ResponseEntity<?> editProfileGame(@PathVariable String id, @RequestBody ProfileGame updatedProfileGame) {

        try {
            if (profileService.existingProfile(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
            }

            Profile profile = profileService.getProfileByProfilegameName(updatedProfileGame.getName());
            if (profile != null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Name has already to use");

            }

            profileService.updateProfileGame(id, updatedProfileGame);
            return ResponseEntity.ok().body(profileService.findById(id).getProfileGame());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to edit profile game : " + e.getMessage());
        }
    }

}