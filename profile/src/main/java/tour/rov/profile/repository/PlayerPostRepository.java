package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.PlayerPost;

public interface PlayerPostRepository extends MongoRepository<PlayerPost, String> {

}