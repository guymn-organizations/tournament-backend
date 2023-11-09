package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Match;

public interface MatchRepo extends MongoRepository<Match, String> {
}
