package tour.rov.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Image;
import tour.rov.profile.repository.ImageRepository;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public Image getImageById(String id) {
        return imageRepository.findById(id).get();
    }

    public void deleteById(String id) {
        imageRepository.deleteById(id);
    }

    public void deleteAll() {
        imageRepository.deleteAll();
    }
}
