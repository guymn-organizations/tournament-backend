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

import tour.rov.profile.model.Alert;
import tour.rov.profile.model.Match;
import tour.rov.profile.model.Team;
import tour.rov.profile.model.Tournament;
import tour.rov.profile.repository.MatchRepo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class MatchService {
    @Autowired
    private MatchRepo matchRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TournamentService tournamentService;

    public Match findMatchById(String id) {
        Optional<Match> match = matchRepo.findById(id);
        return match.orElse(null);
    }

    public List<Match> findMatchesByTeamId(String teamId, int pageIndex, int pageSize) {
        LocalDateTime now = LocalDateTime.now();

        Criteria criteria = new Criteria().orOperator(
                Criteria.where("teamA.id").is(teamId),
                Criteria.where("teamB.id").is(teamId));

        checkConfirmWiner(criteria.and("startDate").lt(now));

        criteria = criteria.and("startDate").gt(now);

        Query query = new Query(criteria).with(Sort.by(Sort.Order.asc("startDate"))).skip(pageIndex * pageSize)
                .limit(pageSize);

        return mongoTemplate.find(query, Match.class);
    }

    public void checkConfirmWiner(Criteria criteriaOut) {
        Query query = new Query(criteriaOut);
        List<Match> mList = mongoTemplate.find(query, Match.class);

        for (Match match : mList) {
            Tournament tournament = tournamentService.findById(match.getId());
            Boolean bool = false;
            for (int i = 0; i < 2; i++) {
                if (match.getResultA()[i] != match.getResultA()[i]) {
                    bool = true;
                    break;
                }
            }

            if (bool) {
                // alert คะแนนไม่ตรง
                Alert alert = new Alert();
                alert.setMatch(match);
                alert.setContent("The scores reported by the two teams do not match.");
                tournament.getAlerts().add(alert);

            } else {
                if (match.getResultA()[0] > match.getResultA()[1] && match.getResultA()[0] > match.getBo() / 2) {
                    // A Win
                    if (match.getNextMatch() == null) {
                        // A champ
                        Alert alert = new Alert();
                        alert.setMatch(match);
                        alert.setContent("The winner of tournament " + tournament.getName() + " is team "
                                + match.getTeamA().getName() + ".");
                        tournament.getAlerts().add(alert);
                        return;
                    }
                    addPlayerToMatch(match, match.getTeamA());
                } else if (match.getResultA()[0] < match.getResultA()[1] && match.getResultA()[1] > match.getBo() / 2) {
                    // B Win
                    if (match.getNextMatch() == null) {
                        // B champ
                        Alert alert = new Alert();
                        alert.setMatch(match);
                        alert.setContent("The winner of tournament " + tournament.getName() + " is team "
                                + match.getTeamB().getName() + ".");
                        tournament.getAlerts().add(alert);
                        return;
                    }
                    addPlayerToMatch(match, match.getTeamB());
                } else {
                    // alert คะแนนเพื้ยน
                    Alert alert = new Alert();
                    alert.setMatch(match);
                    alert.setContent("The scores reported by both teams were abnormal.");
                    tournament.getAlerts().add(alert);
                }

            }

            tournamentService.saveTournament(tournament);

        }
    }

    public void addPlayerToMatch(Match match, Team team) {

        String matchId = match.getNextMatch();
        Match nextMath = findMatchById(matchId);
        if (nextMath.getTeamA() == null) {
            nextMath.setTeamA(team);
        } else if (nextMath.getTeamB() == null) {
            nextMath.setTeamB(team);
        }

        saveMatch(nextMath);
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

    public List<String> generateMatches(String tourId, int BO, LocalDate date, int num) {
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
                match.setTourId(tourId);
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

    public Page<Match> findByRoundAllById(int round, List<String> idList, Pageable pageable) {
        return matchRepo.findAllByRoundAndIdIn(round, idList, pageable);
    }

}
