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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Message;
import tour.rov.profile.model.PositionType;
import tour.rov.profile.service.MessageService;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<?> getMessage(@RequestParam List<String> id) {
        try {
            List<Message> messages = messageService.findAllById(id);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("{team_name}/INVITE_TO_JOIN_TEAM/{profile_game_name}")
    public ResponseEntity<?> sendInviteToJoinTeam(@PathVariable String team_name,
            @PathVariable String profile_game_name, @RequestBody PositionType positionType) {
        try {
            messageService.inviteToJoinTeam(team_name, profile_game_name, positionType);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("{team_name}/REQUEST_TO_JOIN_TEAM/{profile_game_name}")
    public ResponseEntity<?> sendReqToJoinTeam(@PathVariable String team_name, @PathVariable String profile_game_name,
            @RequestBody String positionType) {
        try {
            PositionType positionTypeEnum = PositionType.valueOf(positionType);
            messageService.requestToJoinTeam(team_name, profile_game_name, positionTypeEnum);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("{team_nameA}/INVITE_TO_SCRIMS/{team_nameB}")
    public ResponseEntity<?> sendInviteToScrims(@PathVariable String team_nameA,
            @PathVariable String team_nameB,
            @RequestBody String scrims_id) {
        try {
            messageService.inviteToScrims(team_nameA, team_nameB, scrims_id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
