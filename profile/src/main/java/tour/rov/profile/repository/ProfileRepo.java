package tour.rov.profile.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.entity.Profile;

public interface ProfileRepo extends MongoRepository<Profile, String> {
}
