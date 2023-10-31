package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.TeamInTournament;

public interface TeamInTournamentRepo extends MongoRepository<TeamInTournament, String>{
    
}
