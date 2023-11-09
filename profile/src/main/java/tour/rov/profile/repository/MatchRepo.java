package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Match;

public interface MatchRepo extends MongoRepository<Match, String> {
    Page<Match> findAllByRoundAndIdIn(int round, List<String> idList, Pageable pageable);

}
