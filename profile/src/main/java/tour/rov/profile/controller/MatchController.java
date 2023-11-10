package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Match;
import tour.rov.profile.service.MatchService;

@RestController
@RequestMapping("match")
@CrossOrigin
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("/{team_id}")
    public ResponseEntity<?> getMatchByTeam(@PathVariable String team_id,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "7") int pageSize) {
        try {
            List<Match> matchesForTeam = matchService.findMatchesByTeamId(team_id, pageIndex, pageSize);

            return ResponseEntity.ok(matchesForTeam);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve matches for the team: " + e.getMessage());
        }
    }

    @GetMapping("/round/{round}")
    public ResponseEntity<?> getMatchByRound(@PathVariable int round,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "8") int pageSize,
            @RequestParam List<String> idList) {
        try {
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<Match> matchesPage = matchService.findByRoundAllById(round, idList, pageable);

            return ResponseEntity.ok(matchesPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve matches for the team: " + e.getMessage());
        }
    }

    @PutMapping("/{match_id}/results")
    public ResponseEntity<?> setMatchResult(
            @PathVariable String match_id,
            @RequestParam String team,
            @RequestParam String resultType,
            @RequestParam(defaultValue = "0") int score) {
        try {
            Match match = matchService.findMatchById(match_id);

            if ("teamA".equals(team)) {
                if ("resultA".equals(resultType)) {
                    match.getResultA()[0] += score;
                } else if ("resultB".equals(resultType)) {
                    match.getResultA()[1] += score;
                }
            } else if ("teamB".equals(team)) {
                if ("resultA".equals(resultType)) {
                    match.getResultB()[0] += score;
                } else if ("resultB".equals(resultType)) {
                    match.getResultB()[1] += score;
                }
            }

            return ResponseEntity.ok(match);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve matches for the team: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getmatchById(@PathVariable String id) {
        try {
            Match match = matchService.findMatchById(id);

            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found.");
            }

            return ResponseEntity.ok(match);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve the match: " + e.getMessage());
        }
    }

}
