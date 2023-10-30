package tour.rov.profile.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Advert;

public interface AdvertRepo extends MongoRepository<Advert, String>{
    List<Advert> findByTime(LocalTime time);
}
