package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import tour.rov.profile.model.Team;
import tour.rov.profile.repository.TeamRepository;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<?> getAllTeams() {
        try {
            // Retrieve all teams from the database
            List<Team> teams = teamRepository.findAll();

            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return new ResponseEntity<>("Failed to get teams: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable String teamId) {
        try {
            // Attempt to retrieve the Team from the database by its ID
            Team team = teamRepository.findById(teamId).orElse(null);

            if (team != null) {
                return new ResponseEntity<>(team, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return new ResponseEntity<>("Failed to get the team: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody Team team, UriComponentsBuilder uriBuilder) {
        try {
            // Save the team to the MongoDB database
            Team savedTeam = teamRepository.save(team);

            // Create the URI for the newly created team
            uriBuilder.path("/teams/{id}").buildAndExpand(savedTeam.getId()).toUriString();

            return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return new ResponseEntity<>("Failed to create the team: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
