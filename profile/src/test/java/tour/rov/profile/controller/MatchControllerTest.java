package tour.rov.profile.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tour.rov.profile.model.Chat;
import tour.rov.profile.model.Match;
import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.service.MatchService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchControllerTest {

    @InjectMocks
    private MatchController matchController;

    @Mock
    private MatchService matchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMatchByTeam_Success() {
        // Create an example team ID and a list of matches
        String teamId = "1";
        List<Match> exampleMatches = new ArrayList<>();
        exampleMatches.add(new Match("Round 1", new TeamInTournament(), new TeamInTournament()));
        exampleMatches.get(0).setStartDate(LocalDate.now());

        // Mock the behavior of the MatchService to simulate a successful operation
        Mockito.when(matchService.findMatchesByTeamId(teamId, 0, 10)).thenReturn(exampleMatches);

        // Perform the GET request
        ResponseEntity<?> response = matchController.getMatchByTeam(teamId, 0, 10);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody() instanceof List;
        assert ((List<?>) response.getBody()).size() == 1;
    }

    @Test
    public void testGetMatchByTeam_Failure() {
        // Mock the behavior of the MatchService to simulate an exception
        Mockito.when(matchService.findMatchesByTeamId("2", 0, 10)).thenThrow(new RuntimeException("Simulated error"));

        // Perform the GET request
        ResponseEntity<?> response = matchController.getMatchByTeam("2", 0, 10);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testCreateReport_Success() {
        // // Create an example match ID and report
        // String matchId = "3";
        // String report = "Match report";

        // // Mock the behavior of the MatchService to simulate a successful operation
        // Match exampleMatch = new Match("Round 2", new TeamInTournament(), new TeamInTournament());
        // Mockito.when(matchService.findMatchById(matchId)).thenReturn(exampleMatch);
        // Mockito.doNothing().when(matchService).saveMatch(exampleMatch);

        // // Perform the POST request
        // ResponseEntity<?> response = matchController.createReport(matchId, report);

        // // Assert the response
        // assert response.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testCreateReport_MatchNotFound() {
        // Mock the behavior of the MatchService to return null (match not found)
        Mockito.when(matchService.findMatchById("4")).thenReturn(null);

        // Perform the POST request
        ResponseEntity<?> response = matchController.createReport("4", "Report");

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.NOT_FOUND);
    }

    // Additional test methods for other controller methods can be added similarly.
}
