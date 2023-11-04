package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Message;
import tour.rov.profile.service.MessageService;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<?> getMessage(@RequestBody List<String> id) {
        try {
            List<Message> messages = messageService.findAllById(id);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("{team_name}/INVITE_TO_JOIN_TEAM/{profile_id}")
    public ResponseEntity<?> sendInviteToJoinTeam(@PathVariable String team_name, @PathVariable String profile_id ,@RequestBody String content) {
        try {
            messageService.inviteToJoinTeam(team_name,profile_id,content);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
