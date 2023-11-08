package tour.rov.profile.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Chat;
import tour.rov.profile.service.ChatService;

public class ChatcontrollerTest {

    @InjectMocks
    private Chatcontroller chatcontroller;

    @Mock
    private ChatService chatService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddContent_Success() {
        // Create an example Chat object
        Chat exampleChat = new Chat("John", "Hello, how are you?", LocalDate.now());

        // Mock the behavior of the ChatService to simulate a successful operation
        Mockito.doNothing().when(chatService).addChatContent(exampleChat);

        // Perform the POST request
        ResponseEntity<String> response = chatcontroller.addContent(exampleChat);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody().equals("Chat content added successfully");
    }

    @Test
    public void testAddContent_Failure() {
        // Create an example Chat object
        Chat exampleChat = new Chat("Alice", "This is an error", LocalDate.now());

        // Mock the behavior of the ChatService to simulate an exception
        Mockito.doThrow(new RuntimeException("Simulated error")).when(chatService).addChatContent(exampleChat);

        // Perform the POST request
        ResponseEntity<String> response = chatcontroller.addContent(exampleChat);

        // Assert the response
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
        assert response.getBody().contains("Failed to add chat content");
    }
}
