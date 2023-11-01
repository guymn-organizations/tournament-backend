package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Advert;

public interface AdvertRepo extends MongoRepository<Advert, String>{
}