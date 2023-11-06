package tour.rov.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.model.Tournament;
import tour.rov.profile.repository.TeamInTournamentRepo;
import tour.rov.profile.repository.TournamentRepo;

@Service
public class TeamInTournamentService {

    @Autowired
    private TeamInTournamentRepo TeamInTournamentRepo;

    public void save(TeamInTournament TeamInTournament) {
        TeamInTournamentRepo.save(TeamInTournament);
    }
}
