package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Team;
import tour.rov.profile.model.Tournament;
import tour.rov.profile.service.TeamService;
import tour.rov.profile.service.TournamentService;

@RestController
@RequestMapping("tournament")
@CrossOrigin
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamService teamService;

    // @PostMapping("/create/{profileId}")
    // public ResponseEntity<?> createTournament(@PathVariable String profileId, @RequestBody Tournament tournament) {
    //     try {
    //         tournamentService.createTournament(tournament, profileId);
    //         return ResponseEntity.status(HttpStatus.CREATED).body(tournamentService.findById(tournament.getId()));
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Failed to create : " + e.getMessage());
    //     }
    // }

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
    public ResponseEntity<?> getAllTournament(@RequestParam int pageIndex, @RequestParam int pageSize) {
        try {
            List<Tournament> tournaments = tournamentService.getAllTournaments(pageIndex, pageSize);

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

    // @PostMapping("/{id}/teamJoin/{teamid}")
    // public ResponseEntity<?> addTeamToTournament(@PathVariable String id, @PathVariable String teamid) {
    //     try {
    //         Tournament tournament = tournamentService.findById(id);
    //         Team team = teamService.findById(teamid);

    //         team.getTournamentId().add(id);
    //         tournament.getTeamJoin().add(teamid);

    //         teamService.saveTeam(team);
    //         tournamentService.saveTournament(tournament);

    //         return ResponseEntity.ok(tournament);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Failed to add the team to the tournament: " + e.getMessage());
    //     }
    // }

    @PostMapping("/{id}/teamJoin/{teamid}")
    public ResponseEntity<?> addTeamToTournament(@PathVariable String id, @PathVariable String teamid) {
        try {
            Tournament tournament = tournamentService.findById(id);
            Team team = teamService.findById(teamid);

            if (tournament == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found with ID: " + id);
            }

            if (team == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found with ID: " + teamid);
            }

            // Check if the team has already joined the tournament
            if (tournament.getTeamJoin().contains(teamid)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team is already part of the tournament.");
            }

            // Check if the team has at least 5 players
            if (team.getPositions().stream().filter(position -> position.getPlayer() != null).count() < 5) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team must have at least 5 players to join the tournament.");
            }

            // Add the team to the tournament and the tournament to the team
            tournament.getTeamJoin().add(teamid);
            team.getTournamentId().add(id);

            // Save the changes
            tournamentService.saveTournament(tournament);
            teamService.saveTeam(team);

            return ResponseEntity.ok(tournament);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add the team to the tournament: " + e.getMessage());
        }
    }



}
