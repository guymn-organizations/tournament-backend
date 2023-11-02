package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tour.rov.profile.model.Image;
import tour.rov.profile.model.Message;
import tour.rov.profile.model.Profile;
import tour.rov.profile.model.ProfileGame;
import tour.rov.profile.repository.ProfileRepo;

@Service
@Transactional
public class ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private ImageService imageService;

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
        if (updatedProfile.getMessages() != null) {
            profile.setMessages(updatedProfile.getMessages());
        }

        if (updatedProfile.getImageProfileUrl() != null) {
            if (profile.getImageProfileUrl() != null) {
                Image image = imageService.getImageById(profile.getImageProfileUrl());

                if (image.getImageUrl().equals(updatedProfile.getImageProfileUrl())) {
                    saveProfile(profile);
                    return;
                }

                imageService.deleteById(image.getId());
            }

            Image imageToSave = new Image();
            imageToSave.setImageUrl(updatedProfile.getImageProfileUrl());
            imageService.saveImage(imageToSave);

            profile.setImageProfileUrl(imageToSave.getId());
        }

        saveProfile(profile);
    }

    public void updateProfileGame(String id, ProfileGame updatedProfileGame) {
        Profile profile = findById(id);

        if (profile.getProfileGame() == null) {
            profile.setProfileGame(new ProfileGame());
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
                Image image = imageService.getImageById(profile.getProfileGame().getImageGameUrl());

                if (image.getImageUrl().equals(updatedProfileGame.getImageGameUrl())) {
                    saveProfile(profile);
                    return;
                }

                imageService.deleteById(image.getId());

                Image imageToSave = new Image();
                imageToSave.setImageUrl(updatedProfileGame.getImageGameUrl());
                imageService.saveImage(imageToSave);

                profile.getProfileGame().setImageGameUrl(imageToSave.getId());
            }

            saveProfile(profile);
        }

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
