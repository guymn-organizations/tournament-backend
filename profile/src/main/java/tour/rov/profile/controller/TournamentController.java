package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Match;
import tour.rov.profile.model.Team;
import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.model.Tournament;
import tour.rov.profile.service.TeamService;
import tour.rov.profile.service.TournamentService;

@RestController
@RequestMapping("tournament")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamService teamService;

    @PostMapping("/create")
    public ResponseEntity<?> createTournament(@RequestBody Tournament tournament) {
        try {

            tournamentService.createTournament(tournament);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tournament was created\n" + tournament);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/change_status")
    public ResponseEntity<?> changeStatus(@PathVariable String tournament_id, @RequestBody Tournament status) {
        try {
            if (tournamentService.existingTournament(tournament_id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found");
            }
            tournamentService.updateStatus(tournament_id, status);
            return ResponseEntity.ok().body("Status was updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to edit Tournament status : " + e.getMessage());
        }
    }

    @PostMapping("/{tournament_id}/matching")
    // สร้าง match และ push ลงตัวแปร matchList
    public ResponseEntity<?> matching(@PathVariable String tournament_id) {
        try {
            Tournament tournament = tournamentService.findById(tournament_id);

            if (tournament == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found");
            }
            List<Match> newMatch = tournamentService.createMatchesForAllTeams(tournament_id);
            tournament.getMatchList().addAll(newMatch);

            tournamentService.saveTournament(tournament);

            return ResponseEntity.ok().body("Match created and added to the tournament's matchList");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create and add a match to the tournament: " + e.getMessage());
        }
    }

    @GetMapping("/Featured")
    // หา tournament ที่มี reward สูงที่สุด
    public ResponseEntity<?> getTournamentWithHighestReward() {
        try {
            Tournament featuredTournament = tournamentService.findTournamentWithHighestReward();

            if (featuredTournament == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No featured tournaments found.");
            }

            return ResponseEntity.ok(featuredTournament);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve featured tournament: " + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllTournament() {
        try {
            List<Tournament> tournaments = tournamentService.getAllTournaments();
            
            if (tournaments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tournaments found.");
            }

            return ResponseEntity.ok(tournaments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve tournaments: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getTournamentById(@PathVariable String id) {
        try {
            Tournament tournament = tournamentService.findById(id);
            
            if (tournament == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found with ID: " + id);
            }

            return ResponseEntity.ok(tournament);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve the tournament: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/teamJoin/{teamId}")
    public ResponseEntity<?> addTeamToTournament(@PathVariable String id, @PathVariable String teamId) {
        try {
            Tournament tournament = tournamentService.findById(id);
            Team team = teamService.findById(teamId);

            TeamInTournament teamJoin = new TeamInTournament(team, 0, 0, 0);

            tournament.getTeamJoin().add(teamJoin);

            tournamentService.saveTournament(tournament);

            return ResponseEntity.ok("Team added to the tournament successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add the team to the tournament: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/team")
    public ResponseEntity<?> getAllTeamInTournament(@PathVariable String id) {
        try {
            Tournament tournament = tournamentService.findById(id);

            if (tournament == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
            }

            List<TeamInTournament> teamsInTournament = tournament.getTeamJoin();

            if (teamsInTournament == null || teamsInTournament.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No teams found in the tournament.");
            }

            return ResponseEntity.ok(teamsInTournament);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to retrieve teams in the tournament: " + e.getMessage());
        }
    }


}
