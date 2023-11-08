package tour.rov.profile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Chat;
import tour.rov.profile.model.Match;
import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.model.Tournament;
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

    public List<Match> findMatchesByTeamId(String teamId, int pageIndex, int pageSize) {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where("teamA.team.id").is(teamId),
            Criteria.where("teamB.team.id").is(teamId)
        );

        Query query = new Query(criteria).skip(pageIndex * pageSize).limit(pageSize);
        return mongoTemplate.find(query, Match.class);
    }

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

    public List<Match> generateMatches(List<TeamInTournament> teamsInTournament) {
        List<Match> matches = new ArrayList<>();

        int round = 1;
        while (teamsInTournament.size() > 1) {
            List<Match> roundMatches = new ArrayList<>();
            for (int i = 0; i < teamsInTournament.size(); i += 2) {
                TeamInTournament team1 = teamsInTournament.get(i);
                TeamInTournament team2 = teamsInTournament.get(i + 1);
                Match match = new Match("Round " + round, team1, team2);
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

            TeamInTournament winner = determineWinnerByChatConfirm(match);
            winners.add(winner);

        }
        return winners;
    }

    public TeamInTournament determineWinnerByChatConfirm(Match match) {

        List<Chat> chatList = match.getChat();

        boolean teamAConfirmed = false;
        boolean teamBConfirmed = false;

        for (Chat chat : chatList) {
            if (chat.getSender().equals(match.getTeamA()) && chat.getContent().equals("confirm")) {
                teamAConfirmed = true;
            }

            if (chat.getSender().equals(match.getTeamB()) && chat.getContent().equals("confirm")) {
                teamBConfirmed = true;
            }
        }

        if (teamAConfirmed) {
            return match.getTeamA();
        } else if (teamBConfirmed) {
            return match.getTeamB();
        } else {
            return null;
        }
    }


    public List<Match> getAllMatchesForTournament(Tournament tournament) {
        return matchRepo.findMatchesByTournament(tournament);
    }

}
