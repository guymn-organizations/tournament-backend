package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tour.rov.profile.model.Message;
import tour.rov.profile.model.Profile;
import tour.rov.profile.model.ProfileGame;
import tour.rov.profile.model.Team;
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
        return !profileRepo.existsById(profileId);
    }

    public boolean existingProfileByEmail(String email) {
        return profileRepo.existsByEmail(email);
    }

    public Profile getProfileByEmail(String email) {
        return profileRepo.getProfileByEmail(email);
    }

    public void updateProfile(String id, Profile updatedProfile) {
        Profile profile = findById(id);

        if (updatedProfile.getBirthday() != null) {
            profile.setBirthday(updatedProfile.getBirthday());
        }
        if (updatedProfile.getEmail() != null) {
            profile.setEmail(updatedProfile.getEmail());
        }
        if (updatedProfile.getFirstName() != null) {
            profile.setFirstName(updatedProfile.getFirstName());
        }
        if (updatedProfile.getLastName() != null) {
            profile.setLastName(updatedProfile.getLastName());
        }
        if (updatedProfile.getGender() != null) {
            profile.setGender(updatedProfile.getGender());
        }
        if (updatedProfile.getPassword() != null) {
            profile.setPassword(updatedProfile.getPassword());
        }
        if (updatedProfile.getImageProfileUrl() != null) {
            profile.setImageProfileUrl(updatedProfile.getImageProfileUrl());
        }
        if (updatedProfile.getProfileGame() != null) {
            profile.setProfileGame(updatedProfile.getProfileGame());
        }
        if (updatedProfile.getMessages() != null) {
            profile.setMessages(updatedProfile.getMessages());
        }

        saveProfile(profile);
    }

    public void updateProfileGame(String id, ProfileGame updatedProfileGame) {
        Profile profile = findById(id);

        if (profile.getProfileGame() == null) {
            profile.setProfileGame(updatedProfileGame);
        } else {
            if (updatedProfileGame.getName() != null) {
                profile.getProfileGame().setName(updatedProfileGame.getName());
            }
            if (updatedProfileGame.getOpenId() != null) {
                profile.getProfileGame().setOpenId(updatedProfileGame.getOpenId());
            }
            if (updatedProfileGame.getMyTeam() != null) {
                profile.getProfileGame().setMyTeam(updatedProfileGame.getMyTeam());
            }
            if (updatedProfileGame.getImageGameUrl() != null) {
                profile.getProfileGame().setImageGameUrl(updatedProfileGame.getImageGameUrl());
            }
        }

        updateProfile(id, profile);
    }

    public List<Message> getMessages(String id) {
        Profile profile = findById(id);
        return profile.getMessages();
    }

    public void addMeaasge(String id, Message message) {
        Profile profile = findById(id);
        profile.getMessages().add(message);
        saveProfile(profile);
    }

}
