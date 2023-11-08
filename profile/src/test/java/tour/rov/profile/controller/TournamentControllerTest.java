package tour.rov.profile.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tour.rov.profile.controller.TournamentController;
import tour.rov.profile.model.Tournament;
import tour.rov.profile.service.TournamentService;

import static org.mockito.Mockito.doNothing;  // Use doNothing() for void methods
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TournamentControllerTest {

    @InjectMocks
    private TournamentController tournamentController;

    @Mock
    private TournamentService tournamentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTournament() {
        // Create a sample Tournament object
        Tournament sampleTournament = new Tournament();
        sampleTournament.setName("Sample Tournament");
        sampleTournament.setDetail("Sample Tournament Details");
        sampleTournament.setReward(1000.0);

        // Mock the service method to do nothing (void method)
        doNothing().when(tournamentService).createTournament(sampleTournament);

        // Call the controller method
        ResponseEntity<?> response = tournamentController.createTournament(sampleTournament);

        // Assert that the response status is HttpStatus.CREATED
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    // You can write similar test methods for other controller endpoints
}
