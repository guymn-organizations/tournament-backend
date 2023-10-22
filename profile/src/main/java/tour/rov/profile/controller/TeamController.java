package tour.rov.profile.controller;

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
}
