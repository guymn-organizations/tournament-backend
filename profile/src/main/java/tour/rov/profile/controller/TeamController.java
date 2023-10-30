package tour.rov.profile.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Message;
import tour.rov.profile.model.Position;
import tour.rov.profile.model.Profile;
import tour.rov.profile.model.Team;
import tour.rov.profile.model.Position.PositionType;
import tour.rov.profile.service.TeamService;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody Team team) {
        try {
            if (teamService.existingTeamName(team.getName())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Name is already use");
            }
            // Save the team to the MongoDB database
            team.setMessages(new ArrayList<Message>());
            team.setTeamReserve(new ArrayList<Profile>());
            team.setPositions(new ArrayList<Position>());
            team.getPositions().add(new Position(PositionType.DSL, "DARK SLAYER LANE", null));
            team.getPositions().add(new Position(PositionType.JG, "JUNGLE", null));
            team.getPositions().add(new Position(PositionType.MID, "MID LANE", null));
            team.getPositions().add(new Position(PositionType.ADL, "ABYSSAL DRAGON LANE", null));
            team.getPositions().add(new Position(PositionType.SUP, "SUPPORT", null));

            teamService.saveTeam(team);
            return ResponseEntity.status(HttpStatus.CREATED).body(team);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create team: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable String id) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Team not found");
            }
            Team team = teamService.findById(id);
            return ResponseEntity.ok(team);
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

    @PutMapping("/{id}/DSL")
    public ResponseEntity<?> addDSLPlayer(@PathVariable String id, @RequestBody Profile dslPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.getPositions().get(0).setPlayer(dslPlayer);
            teamService.updateTeam(id, team);

            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added DSL player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/JG")
    public ResponseEntity<?> addJGPlayer(@PathVariable String id, @RequestBody Profile jgPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.getPositions().get(1).setPlayer(jgPlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added JG player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/MID")
    public ResponseEntity<?> addMIDPlayer(@PathVariable String id, @RequestBody Profile midPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.getPositions().get(2).setPlayer(midPlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added MID player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/ADL")
    public ResponseEntity<?> addADLPlayer(@PathVariable String id, @RequestBody Profile adlPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.getPositions().get(3).setPlayer(adlPlayer);
            teamService.updateTeam(id, team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team was added ADL player");
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/SUP")
    public ResponseEntity<?> addSUPPlayer(@PathVariable String id, @RequestBody Profile supPlayer) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            Team team = new Team();
            team.getPositions().get(4).setPlayer(supPlayer);
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
