package tour.rov.profile.controller;

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

import tour.rov.profile.model.Profile;
import tour.rov.profile.service.ProfileService;

@RestController
@RequestMapping("profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Profile profile) {
        try {
            profileService.saveProfile(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body("Profile was created\n" + profile);
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

            } else if (password.equals(profile.getPassword())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worng password");

            }
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to login : " + e.getMessage());
        }
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<?> updateProfile(@PathVariable String profileId, @RequestBody Profile updatedProfile) {

        if (!profileService.existingProfile(profileId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
        }

        Profile profile = profileService.findById(profileId);

        // Update the fields of the existing profile
        profile.setBirthday(updatedProfile.getBirthday());
        profile.setEmail(updatedProfile.getEmail());
        profile.setFirstName(updatedProfile.getFirstName());
        profile.setGender(updatedProfile.getGender());
        profile.setLastName(updatedProfile.getLastName());
        profile.setPassword(updatedProfile.getPassword());
        profile.setImageProfileUrl(updatedProfile.getImageProfileUrl());
        profile.setProfileGame(updatedProfile.getProfileGame());

        // Save the updated profile
        profileService.saveProfile(profile);

        return ResponseEntity.ok().body("Profile was updated");
    }
}