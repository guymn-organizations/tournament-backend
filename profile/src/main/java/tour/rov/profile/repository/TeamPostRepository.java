package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.TeamPost;

public interface TeamPostRepository extends MongoRepository<TeamPost, String> {

}
