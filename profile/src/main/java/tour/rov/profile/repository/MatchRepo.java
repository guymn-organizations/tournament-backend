package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Match;

public interface MatchRepo extends MongoRepository<Match, String>{
    List<Match> findMatchesByid(String id);
}
