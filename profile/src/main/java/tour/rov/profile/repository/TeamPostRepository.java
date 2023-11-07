package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.TeamPost;

public interface TeamPostRepository extends MongoRepository<TeamPost, String> {
    List<TeamPost> findAllBy(Pageable pageable);
}
