package tour.rov.profile.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Match;
import tour.rov.profile.repository.MatchRepo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class MatchService {
    @Autowired
    private MatchRepo matchRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Match findMatchById(String id) {
        Optional<Match> match = matchRepo.findById(id);
        return match.orElse(null);
    }

    public List<Match> findMatchesByTeamId(String teamId, int pageIndex, int pageSize) {
        LocalDateTime now = LocalDateTime.now();

        Criteria criteria = new Criteria().orOperator(
                Criteria.where("teamA.id").is(teamId),
                Criteria.where("teamB.id").is(teamId));

        criteria = criteria.and("startDate").gt(now);

        Query query = new Query(criteria).with(Sort.by(Sort.Order.asc("startDate"))).skip(pageIndex * pageSize)
                .limit(pageSize);

        return mongoTemplate.find(query, Match.class);
    }

    public void saveMatch(Match match) {
        matchRepo.save(match);
    }

    public void saveMatches(List<Match> matches) {
        matchRepo.saveAll(matches);
    }

    public boolean matchExists(String id) {
        return matchRepo.existsById(id);
    }

    // public List<Match> generateMatches(List<Team> teamsInTournament) {
    // List<Match> matches = new ArrayList<>();

    // int round = 1;
    // while (teamsInTournament.size() > 1) {
    // List<Match> roundMatches = new ArrayList<>();
    // for (int i = 0; i < teamsInTournament.size(); i += 2) {
    // Team team1 = teamsInTournament.get(i);
    // Team team2 = teamsInTournament.get(i + 1);
    // Match match = new Match(round, team1, team2);
    // roundMatches.add(match);
    // }
    // matches.addAll(roundMatches);
    // teamsInTournament = getWinners(roundMatches);
    // round++;
    // }

    // return matches;
    // }

    // public List<Team> getWinners(List<Match> matches) {
    // List<Team> winners = new ArrayList<>();
    // for (Match match : matches) {

    // Team winner = confirmWinner(match);
    // winners.add(winner);

    // }
    // return winners;
    // }

    // public Team confirmWinner(Match match) {
    // int[] resultA = match.getResultA();
    // int[] resultB = match.getResultB();
    // int BO = tournament.getBO();

    // // Assuming resultA and resultB are scores
    // if (resultA[0] > resultB[0]) {
    // return match.getTeamA();
    // } else if (resultA[0] < resultB[0]) {
    // return match.getTeamB();
    // } else {
    // // Scores are tied, check tiebreaker condition
    // int resultWinner = getResultWinner(match);

    // // Assuming the tiebreaker condition is result winner > BO/2
    // if (resultWinner > BO / 2) {
    // return match.getTeamA();
    // } else {
    // return match.getTeamB();
    // }
    // }
    // }

    // private int getResultWinner(Match match) {
    // int[] resultA = match.getResultA();
    // int[] resultB = match.getResultB();

    // // Assuming result winner is the sum of scores
    // return resultA[0] + resultB[0];
    // }

    // public List<Match> getAllMatchesForTournament(Tournament tournament) {
    // return matchRepo.findMatchesByTournament(tournament);
    // }

    public List<String> generateMatches(int BO, LocalDate date, int num) {
        int result = (int) (Math.log(num) / Math.log(2));
        List<Match> matchsList = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();

        int loop = 1;
        while (result > 0) {
            for (int j = 0; j < loop; j++) {
                Match match = new Match();
                match.setNextMatch(queue.poll());
                match.setStartDate(date.plusDays(result));
                match.setRound(result);
                match.setBo(BO);
                matchsList.add(match);
                saveMatch(match);

                queue.add(match.getId());
                queue.add(match.getId());
            }
            result--;
            loop = loop * 2;

        }

        List<String> matchIds = matchsList.stream().map(Match::getId).collect(Collectors.toList());
        return matchIds;
    }

}
