package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tour.rov.profile.model.Profile;
import tour.rov.profile.repository.ProfileRepo;

@Service
@Transactional
public class ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    public List<Profile> getAllProfile() {
        return profileRepo.findAll();
    }

    public Profile findById(String id) {
        return profileRepo.findById(id).get();
    }

    public void saveProfile(Profile profile) {
        profileRepo.save(profile);
    }

    public boolean existingProfile(String profileId) {
        return profileRepo.existsById(profileId);
    }

    public Profile getProfileByEmail(String email) {
        return profileRepo.getProfileByEmail(email);
    }

    public void updateProfile() {

    }

}
