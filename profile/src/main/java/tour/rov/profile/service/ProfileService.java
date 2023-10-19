package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tour.rov.profile.entity.Profile;
import tour.rov.profile.repository.ProfileRepo;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    public List<Profile> getAllProfile() {
        return profileRepo.findAll();
    }

    @Transactional
    public void saveProfile(Profile profile) {
        try {
            profileRepo.save(profile);
        } catch (DataAccessException e) {
            
        }
    }

}