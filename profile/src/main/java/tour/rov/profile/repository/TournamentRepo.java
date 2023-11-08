package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Tournament;

public interface TournamentRepo extends MongoRepository<Tournament, String>{
    List<Tournament> findAllBy(Pageable pageable);
}
