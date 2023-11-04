package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.Profile;

public interface ProfileRepo extends MongoRepository<Profile, String> {
    public Profile getProfileByEmail(String email);

    public boolean existsByEmail(String eamil);

    public Profile findByProfileGameName(String name);

}
