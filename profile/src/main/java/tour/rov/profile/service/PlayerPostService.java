package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.PlayerPost;
import tour.rov.profile.repository.PlayerPostRepository;

@Service
public class PlayerPostService {
    @Autowired
    private PlayerPostRepository playerPostRepository;

    public void savePlayerPost(PlayerPost playerPost) {
        playerPostRepository.save(playerPost);
    }

    public List<PlayerPost> getAllPlayerPosts() {
        return playerPostRepository.findAll();
    }

    public PlayerPost findById(String id) {
        return playerPostRepository.findById(id).get();
    }

    public void updatePlayerPost(String id, PlayerPost updatedPlayerPost) {
        PlayerPost playerPost = findById(id);

        if (updatedPlayerPost.getPositions() != null) {
            playerPost.setPositions(updatedPlayerPost.getPositions());
        }
        if (updatedPlayerPost.getProfile() != null) {
            playerPost.setProfile(updatedPlayerPost.getProfile());
        }

        savePlayerPost(playerPost);
    }
}
