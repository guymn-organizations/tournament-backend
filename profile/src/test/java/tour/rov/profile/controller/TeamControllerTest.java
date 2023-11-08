package tour.rov.profile.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tour.rov.profile.controller.TeamController;
import tour.rov.profile.model.Team;
import tour.rov.profile.service.TeamService;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

public class TeamControllerTest {

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTeam() {
        // Team team = new Team(); // Create a Team instance
        // when(teamService.existingTeamName(team.getName())).thenReturn(false);
        // when(teamService.createTeam(any(Team.class))).thenReturn(team);

        // ResponseEntity<?> response = teamController.createTeam(team);

        // Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Assertions.assertEquals(team, response.getBody());

        // verify(teamService).createTeam(any(Team.class));
    }

    @Test
    public void testGetTeamById() {
        Team team = new Team(); // Create a Team instance
        String teamId = "exampleId";
        when(teamService.existingTeam(teamId)).thenReturn(false);
        when(teamService.findById(teamId)).thenReturn(team);

        ResponseEntity<?> response = teamController.getTeamById(teamId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(team, response.getBody());

        verify(teamService).findById(teamId);
    }

    @Test
    public void testDeleteTeam() {
        String teamId = "exampleId";
        when(teamService.existingTeam(teamId)).thenReturn(false);

        ResponseEntity<?> response = teamController.deleteTeam(teamId);

        Assertions.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        Assertions.assertEquals("Team was deleted", response.getBody());

        verify(teamService).deleteTeam(teamId);
    }

    @Test
    public void testAddDSLPlayer() {
        // String teamId = "exampleId";
        // String playerName = "PlayerName";
        // when(teamService.existingTeam(teamId)).thenReturn(false);
        // when(teamService.checkHaveTeam(playerName)).thenReturn(false);
        // when(teamService.addPlayer(teamId, 0, playerName)).thenReturn(null);

        // ResponseEntity<?> response = teamController.addDSLPlayer(teamId, playerName);

        // Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // verify(teamService).addPlayer(teamId, 0, playerName);
    }

    // Add more test methods for other endpoints in TeamController

}
