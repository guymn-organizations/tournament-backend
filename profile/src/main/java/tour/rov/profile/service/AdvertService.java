package tour.rov.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Advert;
import tour.rov.profile.model.Image;
import tour.rov.profile.repository.AdvertRepo;

@Service
public class AdvertService {
    @Autowired
    private AdvertRepo advertRepo;

    @Autowired
    private ImageService imageService;

    public void saveAdvert(Advert advert) {
        advertRepo.save(advert);
    }

    public List<Advert> getAllAdverts() {
        return advertRepo.findAll();
    }

    public void createAdvert(Advert advert){
        Image image = new Image();
        image.setImageUrl(advert.getImageAdvertUrl());
        imageService.saveImage(image);

        advert.setImageAdvertUrl(image.getId());
        saveAdvert(advert);
    }
}