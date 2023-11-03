package tour.rov.profile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Scrims;

@SpringBootTest
public class ScrimsControllerTest {

    @Autowired
    private ScrimsController scrimsController;

    @Test
    public void testCreateScrims() {
        Scrims scrims = new Scrims(); // Create a Scrims object with test data

        ResponseEntity<?> response = scrimsController.createScrims(scrims);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetScrimsByTeam() {
        String teamId = "testTeamId";

        ResponseEntity<?> response = scrimsController.getScrimsByTeam(teamId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
