package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    // @GetMapping("/{team_id}")
    // public ResponseEntity<?> getMatchByTeam(@PathVariable String team_id,
    //         @RequestParam(defaultValue = "0") int pageIndex,
    //         @RequestParam(defaultValue = "7") int pageSize) {
    //     try {
    //         // Call the MatchService to retrieve matches for the specified team
    //         List<Match> matchesForTeam = matchService.findMatchesByTeamId(team_id, pageIndex, pageSize);

    //         // Return a ResponseEntity with the list of matches if successful
    //         return ResponseEntity.ok(matchesForTeam);
    //     } catch (Exception e) {
    //         // Return an error response if there's an exception
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Failed to retrieve matches for the team: " + e.getMessage());
    //     }
    // }


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

    // @PostMapping("/{match_id}/report")
    // public ResponseEntity<?> createReport(@PathVariable String match_id,
    // @RequestBody String report){
    // try {
    // Match match = matchService.findMatchById(match_id);

    // if (match == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found");
    // }

    // match.getReport().add(report);

    // matchService.saveMatch(match);

    // return ResponseEntity.ok("Report added to the match");
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Failed to create match report: " + e.getMessage());
    // }
    // }

    // @PostMapping("/{match_id}/set_result_A")
    // public ResponseEntity<?> setResultA(@PathVariable String match_id,
    // @RequestParam int resultA){
    // try {
    // Match match = matchService.findMatchById(match_id);

    // if (match == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found");
    // }

    // match.getResult()[0] = resultA;

    // matchService.saveMatch(match);

    // return ResponseEntity.ok("Result for Team A has been set");
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Failed to set result for Team A: " + e.getMessage());
    // }
    // }

    // @PostMapping("/{match_id}/set_result_B")
    // public ResponseEntity<?> setResultB(@PathVariable String match_id,
    // @RequestParam int resultB){
    // try {
    // Match match = matchService.findMatchById(match_id);

    // if (match == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found");
    // }

    // match.getResult()[1] = resultB;

    // matchService.saveMatch(match);

    // return ResponseEntity.ok("Result for Team B has been set");
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Failed to set result for Team B: " + e.getMessage());
    // }
    // }

    // @PostMapping("/{match_id}/send_result")
    // //ส่งแต้มไปบันทึกที่ TeamInTournamnet score
    // public ResponseEntity<?> sendResult(@PathVariable String match_id){
    // try {
    // Match match = matchService.findMatchById(match_id);

    // if (match == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found");
    // }

    // int[] result = match.getResult();

    // TeamInTournament teamA = match.getTeamA();
    // TeamInTournament teamB = match.getTeamB();

    // if (result.length == 2) {
    // teamA.setScore(teamA.getScore() + result[0]);
    // teamB.setScore(teamB.getScore() + result[1]);

    // matchService.updateTeamInTournament(teamA);
    // matchService.updateTeamInTournament(teamB);

    // return ResponseEntity.ok("Match results have been sent and scores updated");
    // } else {
    // return ResponseEntity.badRequest().body("Invalid match result format");
    // }
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Failed to send match results and update scores: " + e.getMessage());
    // }
    // }

    // @PostMapping("/{match_id}/send_chat")
    // //push Chat ใน match
    // public ResponseEntity<?> sendChat(@PathVariable String match_id, @RequestBody
    // Chat chat){
    // try {
    // Match match = matchService.findMatchById(match_id);

    // if (match == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found");
    // }

    // List<Chat> chatList = match.getChat();

    // chatList.add(chat);

    // match.setChat(chatList);

    // matchService.saveMatch(match);

    // return ResponseEntity.ok("Chat message added to the match");
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Failed to send chat message and update match: " + e.getMessage());
    // }
    // }

    @GetMapping("/{id}")
    public ResponseEntity<?> getmatchById(@PathVariable String id){
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
