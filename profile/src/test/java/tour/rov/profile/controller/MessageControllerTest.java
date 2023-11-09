package tour.rov.profile.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tour.rov.profile.model.Message;
import tour.rov.profile.model.PositionType;
import tour.rov.profile.service.MessageService;

import java.util.Arrays;
import java.util.List;

public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMessage_Success() {
        // Create a list of example message IDs
        List<String> messageIds = Arrays.asList("1", "2", "3");

        // Create example messages
        List<Message> exampleMessages = Arrays.asList(
            new Message(Message.MessageType.INVITE_TO_JOIN_TEAM),
            new Message(Message.MessageType.REQUEST_TO_JOIN_TEAM),
            new Message(Message.MessageType.SYSTEM_ALERT)
        );

        // Mock the behavior of the MessageService to simulate a successful operation
        Mockito.when(messageService.findAllById(messageIds)).thenReturn(exampleMessages);

        // Perform the GET request
        ResponseEntity<?> response = messageController.getMessage(messageIds);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody() instanceof List;
        assert ((List<?>) response.getBody()).size() == 3;
    }

    @Test
    public void testGetMessage_Failure() {
        // Mock the behavior of the MessageService to simulate an exception
        Mockito.when(messageService.findAllById(Arrays.asList("4", "5"))).thenThrow(new RuntimeException("Simulated error"));

        // Perform the GET request
        ResponseEntity<?> response = messageController.getMessage(Arrays.asList("4", "5"));

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testReadMessage_Success() {
        // Create an example message ID
        String messageId = "6";

        // Create an example message
        Message exampleMessage = new Message(Message.MessageType.REQUEST_TO_SCRIMS);
        exampleMessage.setId(messageId);

        // Mock the behavior of the MessageService to simulate a successful operation
        Mockito.when(messageService.findById(messageId)).thenReturn(exampleMessage);
        Mockito.doNothing().when(messageService).saveMessage(exampleMessage);

        // Perform the PUT request
        ResponseEntity<?> response = messageController.readMessage(messageId);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert exampleMessage.getIsRead(); // Check that the message is marked as read
    }

    @Test
    public void testReadMessage_MessageNotFound() {
        // Mock the behavior of the MessageService to return null (message not found)
        Mockito.when(messageService.findById("7")).thenReturn(null);

        // Perform the PUT request
        ResponseEntity<?> response = messageController.readMessage("7");

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.NOT_FOUND);
    }

    // Additional test methods for other controller methods can be added similarly.
}
