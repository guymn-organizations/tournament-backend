package tour.rov.profile.service;

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
}
