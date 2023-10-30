package tour.rov.profile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Match;
import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.repository.MatchRepo;
import tour.rov.profile.repository.TeamInTournamentRepo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class MatchService {
    @Autowired
    private MatchRepo matchRepo;
    
    @Autowired
    private TeamInTournamentRepo teamInTournamentRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Match findMatchById(String id) {
        Optional<Match> match = matchRepo.findById(id);
        return match.orElse(null);
    }

    public List<Match> findMatchesByTeamId(String teamId) {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where("teamA.team.id").is(teamId),
            Criteria.where("teamB.team.id").is(teamId)
        );

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Match.class);
    }

    public void saveMatch(Match match) {
        matchRepo.save(match);
    }

    public void updateTeamInTournament(TeamInTournament teamInTournament) {
        teamInTournamentRepo.save(teamInTournament);
    }

    public boolean matchExists(String id) {
        return matchRepo.existsById(id);
    }
}
