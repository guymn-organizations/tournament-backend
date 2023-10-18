package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.repository.ProfileRepo;
import tour.rov.profile.entity.Profile;

@RestController
@RequestMapping("profile")
public class ProfileController {
    @Autowired
    private ProfileRepo profileRepo;

    @GetMapping
    public List<Profile> getAllProfile() {
        return profileRepo.findAll();
    }
}
