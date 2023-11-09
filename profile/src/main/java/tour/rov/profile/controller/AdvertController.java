package tour.rov.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Advert;
import tour.rov.profile.service.AdvertService;

@RestController
@RequestMapping("advert")
@CrossOrigin
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    @GetMapping()
    public ResponseEntity<?> getAllAdvert(@RequestParam int pageIndex, @RequestParam int pageSize) {
        try {
            List<Advert> allAdverts = advertService.getAllAdverts(pageIndex, pageSize);
            return ResponseEntity.ok(allAdverts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve all advertisements: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdvert(@RequestBody Advert advert) {
        try {
            advertService.saveAdvert(advert);
            return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement was created\n" + advert);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create advertisement: " + e.getMessage());
        }
    }
}