package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.PlayerPost;

public interface PlayerPostRepository extends MongoRepository<PlayerPost, String> {
    List<PlayerPost> findAllBy(Pageable pageable);
}