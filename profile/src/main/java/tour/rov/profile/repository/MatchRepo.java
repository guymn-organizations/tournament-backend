package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import tour.rov.profile.model.Match;
import tour.rov.profile.model.Tournament;

public interface MatchRepo extends MongoRepository<Match, String>{
    @Query("SELECT m FROM Match m WHERE m.tournament = :tournament")
    List<Match> findMatchesByTournament(Tournament tournament);
}
