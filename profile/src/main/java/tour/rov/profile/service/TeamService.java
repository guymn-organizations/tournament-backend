package tour.rov.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tour.rov.profile.model.Profile;
import tour.rov.profile.model.Team;
import tour.rov.profile.repository.TeamRepository;

@Service
@Transactional
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProfileService profileService;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public Team findById(String id) {
        return teamRepository.findById(id).get();
    }

    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }

    public boolean existingTeam(String teamId) {
        return !teamRepository.existsById(teamId);
    }

    public boolean existingTeamName(String name) {
        return teamRepository.existsByName(name);
    }

    public void updateTeam(String id, Team updateTeam) {
        Team team = findById(id);

        if (updateTeam.getId() != null) {
            team.setId(updateTeam.getId());
        }
        if (updateTeam.getName() != null) {
            team.setName(updateTeam.getName());
        }
        if (updateTeam.getLeader() != null) {
            team.setLeader(updateTeam.getLeader());
        }
        for (int i = 0; i < team.getPositions().size(); i++) {
            if (updateTeam.getPositions().get(i).getPlayer() != null) {
                team.getPositions().get(i).setPlayer(updateTeam.getPositions().get(i).getPlayer());
            }
        }
        if (updateTeam.getTeamReserve() != null) {
            team.setTeamReserve(updateTeam.getTeamReserve());
        }
        if (updateTeam.getMessages() != null) {
            team.setMessages(updateTeam.getMessages());
        }
        if (updateTeam.getTournamentId() != null) {
            team.setTournamentId(updateTeam.getTournamentId());
        }

        saveTeam(team);
    }

    public void addTourNamentId(String id, String tour_id) {
        Team team = findById(id);
        team.getTournamentId().add(tour_id);
        saveTeam(team);
    }

    public void addTeamReserve(String id, String player) {
        Team team = findById(id);
        Profile profile = profileService.findById(player);

        team.getTeamReserve().add(profile);
        profile.getProfileGame().setMyTeam(id);

        profileService.saveProfile(profile);
        saveTeam(team);
    }

    public void addPlayer(String id, int position, String player_id) {
        Team team = findById(id);
        Profile profile = profileService.findById(player_id);

        team.getPositions().get(position).setPlayer(profile);
        profile.getProfileGame().setMyTeam(id);

        profileService.saveProfile(profile);
        saveTeam(team);
    }

}
