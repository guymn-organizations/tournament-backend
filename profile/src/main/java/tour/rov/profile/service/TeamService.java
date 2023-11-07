package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tour.rov.profile.model.Profile;
import tour.rov.profile.model.Image;
import tour.rov.profile.model.Position;
import tour.rov.profile.model.Team;
import tour.rov.profile.repository.TeamRepository;

@Service
@Transactional
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ImageService imageService;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public Team findById(String id) {
        return teamRepository.findById(id).get();
    }

    public Team findByName(String name) {
        return teamRepository.findByName(name);
    }

    public void createTeam(Team team) {
        if (team.getImageTeamUrl() != null) {
            Image image = new Image();
            image.setImageUrl(team.getImageTeamUrl());
            imageService.saveImage(image);

            team.setImageTeamUrl(image.getId());
        }
        saveTeam(team);
    }

    public void deleteTeam(String id) {
        Team team = findById(id);

        for (Position position : team.getPositions()) {
            if (position.getPlayer() != null) {
                Profile temp = profileService.findById(position.getPlayer().getId());
                temp.getProfileGame().setMyTeam(null);
                profileService.saveProfile(temp);
            }

        }

        for (Profile profile : team.getTeamReserve()) {
            Profile temp = profileService.findById(profile.getId());
            temp.getProfileGame().setMyTeam(null);
            profileService.saveProfile(temp);
        }

        if (team.getImageTeamUrl() != null) {
            Image image = imageService.getImageById(team.getImageTeamUrl());
            imageService.deleteById(image.getId());
        }

        teamRepository.deleteById(id);
    }

    public boolean existingTeam(String teamId) {
        return !teamRepository.existsById(teamId);
    }

    public boolean existingTeamName(String name) {
        return !teamRepository.existsByName(name);
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
        Profile profile = profileService.getProfileByProfilegameName(player);

        team.getTeamReserve().add(profile);
        profile.getProfileGame().setMyTeam(id);

        profileService.saveProfile(profile);
        saveTeam(team);

        // String content = "Player " + profile.getProfileGame().getName() +
        // " joins your team.\nPosition : Reserver";

        // messageService.systemAlertToTeam(team.getName(), content);

        // content = "You are now a member of the " + team.getName() + " team.";

        // messageService.systemAlertToProfile(player, content);
    }

    public void addPlayer(String id, int position, String player_name) {
        Team team = findById(id);
        Profile profile = profileService.getProfileByProfilegameName(player_name);

        if (team.getPositions().get(position).getPlayer() != null) {
            addTeamReserve(id, player_name);
            return;
        }

        team.getPositions().get(position).setPlayer(profile);
        profile.getProfileGame().setMyTeam(id);

        profileService.saveProfile(profile);
        saveTeam(team);

        // String content = "Player " + profile.getProfileGame().getName() +
        // " joins your team.\nPosition : " +
        // team.getPositions().get(position).getPositionName();

        // messageService.systemAlertToTeam(team.getName(), content);

        // content = "You are now a member of the " + team.getName() + " team.";

        // messageService.systemAlertToProfile(player_name, content);
    }

    public void leaveTeam(String id, String player_name) {
        Team team = findById(id);
        Profile profile = profileService.getProfileByProfilegameName(player_name);
        Boolean breaker = true;

        for (Position position : team.getPositions()) {
            if (position.getPlayer() != null && position.getPlayer().getId().equals(profile.getId())) {
                position.setPlayer(null);
                breaker = false;
                break; // Exit the loop as you've found the desired position
            }
        }

        if (breaker) {
            team.getTeamReserve().removeIf(profileR -> profileR.getId().equals(profile.getId()));
        }

        profile.getProfileGame().setMyTeam(null);

        profileService.saveProfile(profile);
        saveTeam(team);
    }

    public List<String> getMessages(String id) {
        Team team = findById(id);
        return team.getMessages();
    }

    public void addMeaasge(String name, String message) {
        Team team = findByName(name);
        team.getMessages().add(0, message);
        saveTeam(team);
    }

    public void addMeaasgeById(String id, String message) {
        Team team = findById(id);
        team.getMessages().add(0, message);
        saveTeam(team);
    }

    public Boolean checkHaveTeam(String player_name) {
        Profile profile = profileService.getProfileByProfilegameName(player_name);
        return profile.getProfileGame().getMyTeam() != null;
    }

    public List<Team> getTeamToshowScrims(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        List<Team> teams = teamRepository.findByPositionsPlayerIsNotNull(pageable);
        return teams;
    }

}
