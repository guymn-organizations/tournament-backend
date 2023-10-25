package tour.rov.profile.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Message;
import tour.rov.profile.model.Profile;
import tour.rov.profile.model.Team;
import tour.rov.profile.service.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody Team team) {
        try {
            // Save the team to the MongoDB database
            team.setMessages(new ArrayList<Message>());
            team.setTeamReserve(new ArrayList<Profile>());
            team.getTeamReserve().add(team.getLeader());
            teamService.saveTeam(team);

            return ResponseEntity.status(HttpStatus.CREATED).body(team);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create team: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable String id) {
        try {
            // Delete the team of the MongoDB database
            teamService.deleteTeam(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Team was deleted");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete team: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/add_dsl")
    public ResponseEntity<?> addDSLPlayer(@PathVariable String id, @RequestBody Profile dslPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.setDSL(dslPlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added DSL player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/add_jg")
    public ResponseEntity<?> addJGPlayer(@PathVariable String id, @RequestBody Profile jgPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.setJG(jgPlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added JG player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/add_mid")
    public ResponseEntity<?> addMIDPlayer(@PathVariable String id, @RequestBody Profile midPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.setMID(midPlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added MID player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/add_adl")
    public ResponseEntity<?> addADLPlayer(@PathVariable String id, @RequestBody Profile adlPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.setADL(adlPlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added ADL player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/add_sup")
    public ResponseEntity<?> addSUPPlayer(@PathVariable String id, @RequestBody Profile supPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.setSUP(supPlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added SUP player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/add_reserve")
    public ResponseEntity<?> addTeamReserve(@PathVariable String id, @RequestBody Profile reservePlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.getTeamReserve().add(reservePlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added SUP player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    // @PutMapping("/{id}/join_tournament")
    // public ResponseEntity<?> joinTournamnet

}