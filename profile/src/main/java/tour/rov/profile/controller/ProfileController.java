package tour.rov.profile.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;
import tour.rov.profile.service.ProfileService;
import tour.rov.profile.entity.Profile;

@RestController
@RequestMapping("profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping
    public ResponseEntity<?> getAllProfile() {
        List<Profile> profiles = profileService.getAllProfile();
        return ResponseEntity.ok(profiles);
    }

    @PostMapping
    public ResponseEntity<?> postProfile(@RequestBody Profile profile) {
        profileService.saveProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body("Profile was created");
    }
}
