package tour.rov.profile.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Profile;

public interface ProfileRepo extends MongoRepository<Profile, String> {
}
