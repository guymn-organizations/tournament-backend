package tour.rov.profile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Team;
import tour.rov.profile.service.ProfileService;

@SpringBootTest
public class TeamControllerTest {
    @Autowired
    private TeamController teamController;

    @Autowired
    private ProfileService profileService;

    @Test
    public void testCreateaTeam() {
        Team team = new Team();
        team.setId("testIdTeam");
        team.setName("Tour");
        team.setLeader(profileService.findById("testId"));
        ResponseEntity<?> response = teamController.createTeam(team);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
