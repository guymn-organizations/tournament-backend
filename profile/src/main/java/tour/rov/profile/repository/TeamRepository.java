package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;

import tour.rov.profile.model.Team;

public interface TeamRepository extends MongoRepository<Team, String> {
    public boolean existsByName(String name);

    public Team findByName(String name);

    List<Team> findAllBy(Pageable pageable);

}
