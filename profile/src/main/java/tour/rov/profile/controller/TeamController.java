package tour.rov.profile.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Position;
import tour.rov.profile.model.Profile;
import tour.rov.profile.model.Team;
import tour.rov.profile.model.PositionType;
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
            if (!teamService.existingTeamName(team.getName())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Name is already use");
            }
            // Save the team to the MongoDB database
            team.setMessages(new ArrayList<String>());
            team.setTeamReserve(new ArrayList<Profile>());
            team.setPositions(new ArrayList<Position>());
            team.setTournamentId(new ArrayList<String>());
            team.getPositions().add(new Position(PositionType.DSL, "DARK SLAYER LANE", null));
            team.getPositions().add(new Position(PositionType.JG, "JUNGLE", null));
            team.getPositions().add(new Position(PositionType.MID, "MID LANE", null));
            team.getPositions().add(new Position(PositionType.ADL, "ABYSSAL DRAGON LANE", null));
            team.getPositions().add(new Position(PositionType.SUP, "SUPPORT", null));

            teamService.createTeam(team);
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

    @GetMapping("/show_scrims")
    public ResponseEntity<?> getTeamToshowScrims(@RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            List<Team> teams = teamService.getTeamToshowScrims(pageIndex, pageSize);
            return ResponseEntity.ok(teams);
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

    @PutMapping("/{id}/DSL/{player_name}")
    public ResponseEntity<?> addDSLPlayer(@PathVariable String id, @PathVariable String player_name) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            if (teamService.checkHaveTeam(player_name)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This player already has a team.");
            }

            teamService.addPlayer(id, 0, player_name);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamService.findById(id));
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add DSL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/JG/{player_name}")
    public ResponseEntity<?> addJGPlayer(@PathVariable String id, @PathVariable String player_name) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            if (teamService.checkHaveTeam(player_name)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This player already has a team.");
            }

            teamService.addPlayer(id, 1, player_name);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamService.findById(id));
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add JG member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/MID/{player_name}")
    public ResponseEntity<?> addMIDPlayer(@PathVariable String id, @PathVariable String player_name) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            if (teamService.checkHaveTeam(player_name)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This player already has a team.");
            }

            teamService.addPlayer(id, 2, player_name);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamService.findById(id));
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add MID member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/ADL/{player_name}")
    public ResponseEntity<?> addADLPlayer(@PathVariable String id, @PathVariable String player_name) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            if (teamService.checkHaveTeam(player_name)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This player already has a team.");
            }

            teamService.addPlayer(id, 3, player_name);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamService.findById(id));
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add ADL member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/SUP/{player_name}")
    public ResponseEntity<?> addSUPPlayer(@PathVariable String id, @PathVariable String player_name) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            if (teamService.checkHaveTeam(player_name)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This player already has a team.");
            }

            teamService.addPlayer(id, 4, player_name);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamService.findById(id));
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add SUP member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/reserver/{reserver}")
    public ResponseEntity<?> addTeamReserve(@PathVariable String id, @PathVariable String reserver) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            if (teamService.checkHaveTeam(reserver)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This player already has a team.");
            }

            teamService.addTeamReserve(id, reserver);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamService.findById(id));
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add reserver member : " + e.getMessage());
        }
    }

    @PutMapping("/{team_name}/out_reserver/{index_reserver}/{index_position}")
    public ResponseEntity<?> outTeamReserve(@PathVariable String team_name, @PathVariable String index_reserver,
            @PathVariable String index_position) {
        try {
            if (teamService.existingTeamName(team_name)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }

            Team team = teamService.findByName(team_name);
            if (team.getPositions().get(Integer.parseInt(index_position)).getPlayer() != null) {
                team.getTeamReserve().add(team.getPositions().get(Integer.parseInt(index_position)).getPlayer());
            }
            team.getPositions().get(Integer.parseInt(index_position))
                    .setPlayer(team.getTeamReserve().get(Integer.parseInt(index_reserver)));
            team.getTeamReserve().remove(Integer.parseInt(index_reserver));
            teamService.saveTeam(team);
            return ResponseEntity.status(HttpStatus.CREATED).body(team);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add reserver member : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/leave/{player_name}")
    public ResponseEntity<?> leaveTeam(@PathVariable String id, @PathVariable String player_name) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }
            teamService.leaveTeam(id, player_name);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to leave teeam : " + e.getMessage());
        }
    }

    @PutMapping("/{id}/set_contact")
    public ResponseEntity<String> setContact(@PathVariable String id, @RequestBody String contact) {
        try {
            if (teamService.existingTeam(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
            }

            Team team = teamService.findById(id);
            team.setContact(contact);
            teamService.saveTeam(team);
            return ResponseEntity.ok(team.getContact());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to set contact : " + e.getMessage());
        }
    }
}
