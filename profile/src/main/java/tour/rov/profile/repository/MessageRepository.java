package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
    public List<Message> findAllByIdIn(List<String> id);
}
