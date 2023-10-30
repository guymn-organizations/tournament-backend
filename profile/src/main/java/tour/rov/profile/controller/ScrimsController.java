package tour.rov.profile.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Chat;
import tour.rov.profile.model.Scrims;
import tour.rov.profile.service.ScrimsService;

@RestController
@RequestMapping("scrims")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class ScrimsController {
    @Autowired
    private ScrimsService scrimsService;

    @PostMapping("/create")
    public ResponseEntity<?> createScrims(@RequestBody Scrims scrims){
        try {
            scrimsService.saveScrims(scrims);
            return ResponseEntity.status(HttpStatus.CREATED).body("Scrimmage was created:\n" + scrims);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create scrimmage: " + e.getMessage());
        }
    }

    @GetMapping("/{team_id}")
    //หาจากทั้งทีม A และ B sort by startDate
    public ResponseEntity<?> getScrimsByTeam(@PathVariable String team_id){
        List<Scrims> scrimsList = scrimsService.findScrimsByTeamId(team_id);
    
        if (!scrimsList.isEmpty()) {
            Collections.sort(scrimsList, (scrims1, scrims2) -> scrims1.getStartDate().compareTo(scrims2.getStartDate()));
            
            return ResponseEntity.ok(scrimsList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No scrimmages found for the team");
        }
    }
    @PostMapping("/{scrims_id}/send_chat")
    //push Chat ใน match
    public ResponseEntity<?> sendChat(@PathVariable String scrims_id, @RequestBody Chat chat){
        try {
            Scrims scrims = scrimsService.findScrimsById(scrims_id);
    
            if (scrims == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Scrimmage not found");
            }
    
            List<Chat> chatList = scrims.getChat();
            chatList.add(chat);
    
            scrimsService.saveScrims(scrims);
    
            return ResponseEntity.ok("Chat message has been sent and added to the scrimmage's chat.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send chat message: " + e.getMessage());
        }
    }
}
