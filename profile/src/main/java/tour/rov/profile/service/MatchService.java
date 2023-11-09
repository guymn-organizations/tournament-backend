package tour.rov.profile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Match;
import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.model.Tournament;
import tour.rov.profile.repository.MatchRepo;
import tour.rov.profile.repository.TeamInTournamentRepo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//     public List<Match> findMatchesByTeamId(String teamId, int pageIndex, int pageSize) {
//     try {
//         // Assuming matchRepository is your repository for managing Match entities
//         // Adjust the method name accordingly based on your repository structure
//         Pageable pageable = PageRequest.of(pageIndex, pageSize);
//         Page<Match> matchesPage = matchRepo.findByTeamId(teamId, pageable);

//         return matchesPage.getContent();
//     } catch (Exception e) {
//         // Handle exceptions as needed
//         throw new RuntimeException("Failed to retrieve matches by team ID: " + e.getMessage(), e);
//     }
// }


    public void saveMatch(Match match) {
        matchRepo.save(match);
    }

    public void saveMatches(List<Match> matches) {
        matchRepo.saveAll(matches);
    }

    public void updateTeamInTournament(TeamInTournament teamInTournament) {
        teamInTournamentRepo.save(teamInTournament);
    }

    public boolean matchExists(String id) {
        return matchRepo.existsById(id);
    }

    public List<Match> generateMatches(List<TeamInTournament> teamsInTournament)
    {
        List<Match> matches = new ArrayList<>();

        int round = 1;
        while (teamsInTournament.size() > 1) {
            List<Match> roundMatches = new ArrayList<>();
            for (int i = 0; i < teamsInTournament.size(); i += 2) {
                TeamInTournament team1 = teamsInTournament.get(i);
                TeamInTournament team2 = teamsInTournament.get(i + 1);
                Match match = new Match(round, team1, team2);
                roundMatches.add(match);
            }
            matches.addAll(roundMatches);
            teamsInTournament = getWinners(roundMatches);
            round++;
        }

        return matches;
    }

    public List<TeamInTournament> getWinners(List<Match> matches) {
        List<TeamInTournament> winners = new ArrayList<>();
        for (Match match : matches) {

            TeamInTournament winner = confirmWinner(match);
            winners.add(winner);

        }
        return winners;
    }

    public TeamInTournament confirmWinner(Match match) {
        int[] resultA = match.getResultA();
        int[] resultB = match.getResultB();
        int BO = Tournament.getBO();
    
        // Assuming resultA and resultB are scores
        if (resultA[0] > resultB[0]) {
            return match.getTeamA();
        } else if (resultA[0] < resultB[0]) {
            return match.getTeamB();
        } else {
            // Scores are tied, check tiebreaker condition
            int resultWinner = getResultWinner(match);
    
            // Assuming the tiebreaker condition is result winner > BO/2
            if (resultWinner > BO / 2) {
                return match.getTeamA();
            } else {
                return match.getTeamB();
            }
        }
    }
    
    private int getResultWinner(Match match) {
        int[] resultA = match.getResultA();
        int[] resultB = match.getResultB();
        
        // Assuming result winner is the sum of scores
        return resultA[0] + resultB[0];
    }
    

    public List<Match> getAllMatchesForTournament(Tournament tournament) {
        return matchRepo.findMatchesByTournament(tournament);
    }

}
