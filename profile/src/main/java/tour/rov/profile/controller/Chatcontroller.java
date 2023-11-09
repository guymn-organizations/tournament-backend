package tour.rov.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Chat;
import tour.rov.profile.service.ChatService;

@RestController
@RequestMapping("chat")
@CrossOrigin
public class Chatcontroller {

    @Autowired
    private ChatService chatService;

    @PostMapping("/content")
    public ResponseEntity<String> addContent(@RequestBody Chat chat) {
        try {
            chatService.addChatContent(chat); 

            return ResponseEntity.ok("Chat content added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add chat content: " + e.getMessage());
        }
    }
}
