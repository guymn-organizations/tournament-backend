package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Scrims;

public interface ScrimsRepo extends MongoRepository<Scrims,String>{
    
}
