package tour.rov.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tour.rov.profile.model.Team;
import tour.rov.profile.repository.TeamRepository;

@Service
@Transactional
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

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
        if (updateTeam.getDSL() != null) {
            team.setDSL(updateTeam.getDSL());
        }
        if (updateTeam.getJG() != null) {
            team.setJG(updateTeam.getJG());
        }
        if (updateTeam.getMID() != null) {
            team.setMID(updateTeam.getMID());
        }
        if (updateTeam.getADL() != null) {
            team.setADL(updateTeam.getADL());
        }
        if (updateTeam.getSUP() != null) {
            team.setSUP(updateTeam.getSUP());
        }
        if (updateTeam.getTeamReserve() != null) {
            team.setTeamReserve(updateTeam.getTeamReserve());
        }
        if (updateTeam.getMessages() != null) {
            team.setMessages(updateTeam.getMessages());
        }

        saveTeam(team);
    }
}
