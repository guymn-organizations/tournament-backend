package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Team;

public interface TeamRepository extends MongoRepository<Team, String> {
    public boolean existsByName(String name);

    public Team findByName(String name);

}
