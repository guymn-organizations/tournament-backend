package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {

}
