package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Chat;

public interface ChatRepo extends MongoRepository<Chat , String>{
    
}
