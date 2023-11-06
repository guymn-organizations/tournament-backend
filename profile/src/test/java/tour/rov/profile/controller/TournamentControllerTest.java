package tour.rov.profile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Tournament;

@SpringBootTest
public class TournamentControllerTest {

    @Autowired
    private TournamentController tournamentController;

    @Test
    public void testCreateTournament() {
        Tournament tournament = new Tournament(); // Create a Tournament object with test data

        ResponseEntity<?> response = tournamentController.createTournament(tournament);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testChangeStatus() {
        String tournamentId = "testTournamentId";
        Tournament status = new Tournament(); // Create a Tournament object with updated status

        ResponseEntity<?> response = tournamentController.changeStatus(tournamentId, status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
