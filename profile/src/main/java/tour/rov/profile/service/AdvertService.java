package tour.rov.profile.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Advert;
import tour.rov.profile.repository.AdvertRepo;

@Service
public class AdvertService {
    @Autowired
    private AdvertRepo advertRepo;

    public void saveAdvert(Advert advert) {
        advertRepo.save(advert);
    }

    public List<Advert> findAdvertsByTime(LocalTime localTime) {
        return advertRepo.findByTime(localTime);
    }
}
