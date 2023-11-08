package tour.rov.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.Image;
import tour.rov.profile.service.ImageService;

@RestController
@RequestMapping("images")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class ImageContriller {
    @Autowired
    private ImageService imageService;

    @PostMapping("/post_image")
    public ResponseEntity<?> createImage(@RequestBody Image image) {
        try {
            if (image.getImageUrl() == null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Must have image");
            }
            imageService.saveImage(image);
            return ResponseEntity.ok(image.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create image : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable String id) {
        try {
            Image image = imageService.getImageById(id);
            return ResponseEntity.ok(image.getImageUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create image : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            imageService.deleteById(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete image : " + e.getMessage());
        }
    }
}
