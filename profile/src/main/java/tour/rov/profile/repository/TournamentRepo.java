package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Tournament;

public interface TournamentRepo extends MongoRepository<Tournament, String>{
    
}
