package tour.rov.profile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Chat;

@SpringBootTest
public class MatchControllerTest {

    @Autowired
    private MatchController matchController;

    @Test
    public void testCreateReport() {
        String matchId = "testMatchId";
        String report = "This is a test report.";

        ResponseEntity<?> response = matchController.createReport(matchId, report);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSetResultA() {
        String matchId = "testMatchId";
        int resultA = 3;

        ResponseEntity<?> response = matchController.setResultA(matchId, resultA);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSetResultB() {
        String matchId = "testMatchId";
        int resultB = 2;

        ResponseEntity<?> response = matchController.setResultB(matchId, resultB);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSendResult() {
        String matchId = "testMatchId";

        ResponseEntity<?> response = matchController.sendResult(matchId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSendChat() {
        String matchId = "testMatchId";
        Chat chat = new Chat(); // Create a chat object with test data

        ResponseEntity<?> response = matchController.sendChat(matchId, chat);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
