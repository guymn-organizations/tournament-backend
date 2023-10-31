package tour.rov.profile.controller;

import java.time.LocalTime;
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
@CrossOrigin(origins = { "http://localhost:4200/" })
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    @GetMapping("/{time}")
    public ResponseEntity<?> getAdvertsByTime(@RequestParam String time) {
    try {
        LocalTime localTime = LocalTime.parse(time);

        List<Advert> adverts = advertService.findAdvertsByTime(localTime);

        if (adverts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No advertisements found for the specified time");
        }

        return ResponseEntity.ok(adverts);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to retrieve advertisements by time: " + e.getMessage());
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
