package tour.rov.profile.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tour.rov.profile.entity.Profile;

public interface ProfileRepo extends CrudRepository<Profile, Long> {
    public List<Profile> findAll();
}
