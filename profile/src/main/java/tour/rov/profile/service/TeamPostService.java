package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.TeamPost;
import tour.rov.profile.repository.TeamPostRepository;

@Service
public class TeamPostService {
    @Autowired
    private TeamPostRepository teamPostRepository;

    public void saveTeamPost(TeamPost teamPost) {
        teamPostRepository.save(teamPost);
    }

    public List<TeamPost> getAllTeamPosts() {
        return teamPostRepository.findAll();
    }

    public TeamPost findById(String id) {
        return teamPostRepository.findById(id).get();
    }

    public void updateTeamPost(String id, TeamPost updatedTeamPost) {
        TeamPost teamPost = findById(id);

        if (updatedTeamPost.getPositions() != null) {
            teamPost.setPositions(updatedTeamPost.getPositions());
        }
        if (updatedTeamPost.getTeam() != null) {
            teamPost.setTeam(updatedTeamPost.getTeam());
        }

        saveTeamPost(teamPost);
    }
}
