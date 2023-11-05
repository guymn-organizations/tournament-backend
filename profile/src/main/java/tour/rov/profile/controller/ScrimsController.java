package tour.rov.profile.controller;

import java.util.Collections;
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

import tour.rov.profile.model.Scrims;
import tour.rov.profile.model.Team;
import tour.rov.profile.service.ScrimsService;
import tour.rov.profile.service.TeamService;

@RestController
@RequestMapping("scrims")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class ScrimsController {
    @Autowired
    private ScrimsService scrimsService;

    @Autowired
    private TeamService teamService;

    @PostMapping("/create")
    public ResponseEntity<?> createScrims(@RequestBody Scrims scrims) {
        try {
            scrimsService.saveScrims(scrims);
            return ResponseEntity.status(HttpStatus.CREATED).body(scrims);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create scrimmage: " + e.getMessage());
        }
    }

    @GetMapping("/{team_id}")
    // หาจากทั้งทีม A และ B sort by startDate
    public ResponseEntity<?> getScrimsByTeam(@PathVariable String team_id) {
        List<Scrims> scrimsList = scrimsService.findScrimsByTeamId(team_id);

        if (!scrimsList.isEmpty()) {
            Collections.sort(scrimsList,
                    (scrims1, scrims2) -> scrims1.getStartDate().compareTo(scrims2.getStartDate()));

            return ResponseEntity.ok(scrimsList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No scrimmages found for the team");
        }
    }

    @PutMapping("/{id}/add_teamB")
    // หาจากทั้งทีม A และ B sort by startDate
    public ResponseEntity<?> setTeamB(@PathVariable String id, @RequestBody String team_name) {
        try {
            if (scrimsService.exsitById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Scrims not found");
            }

            Scrims scrims = scrimsService.findScrimsById(id);
            if (scrims.getTeamB() != null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("This scrims have already opposing team.");
            }
            
            Team teamB = teamService.findByName(team_name);
            scrims.setTeamB(teamB);
            scrimsService.saveScrims(scrims);
            return ResponseEntity.ok(scrims);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
