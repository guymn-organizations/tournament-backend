package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.service.TeamInTournamentService;

@RestController
@RequestMapping("team_in_tournament")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class TeamInTournamentController {
    @Autowired
    private TeamInTournamentService teamInTournamnetService;

    @GetMapping
    public ResponseEntity<?> getAllTeamInTournament(){
        try{
            List<TeamInTournament> teamInTournaments = teamInTournamnetService.getAllTournaments();

            if (teamInTournaments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No teamInTournaments found.");
            }

            return ResponseEntity.ok(teamInTournaments);
        }catch( Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve teamInTournaments: " + e.getMessage());
        }
    }
}

