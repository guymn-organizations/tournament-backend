package tour.rov.profile.controller;

import java.util.List;
import java.util.Optional;


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
@RequestMapping("profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<?> getAllProfile() {
        List<Profile> profiles = profileService.getAllProfile();
        return ResponseEntity.ok(profiles);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerProfile(@RequestBody Profile profile) {
        try {
            profile.setImageProfileUrl(null);
            profile.setProfileGame(null);
            profile.setMessages(null);
            profileService.saveProfile(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body("Profile was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the profile.");
        }
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<?> updateProfile(@PathVariable String profileId, @RequestBody Profile updatedProfile) {
        // Check if the specified profile exists
        Optional<Profile> existingProfile = profileService.findById(profileId);

        if (!existingProfile.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Profile profile = existingProfile.get();

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