package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.repository.TeamInTournamentRepo;

@Service
public class TeamInTournamentService {
    @Autowired
    private TeamInTournamentRepo teamInTournamentRepo;

    public List<TeamInTournament> getAllTournaments() {
        return teamInTournamentRepo.findAll();
    }  
}
