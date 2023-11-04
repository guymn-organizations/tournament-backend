package tour.rov.profile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Scrims;
import tour.rov.profile.repository.ScrimsRepo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class ScrimsService {
    @Autowired
    private ScrimsRepo scrimsRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveScrims(Scrims scrims) {
        scrimsRepo.save(scrims);
    }

    public Scrims findScrimsById(String id) {
        Optional<Scrims> scrims = scrimsRepo.findById(id);
        return scrims.orElse(null);
    }

    public Boolean exsitById(String id) {
        return !scrimsRepo.existsById(id);
    }

    public List<Scrims> findScrimsByTeamId(String teamId) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("teamA.id").is(teamId),
                Criteria.where("teamB.id").is(teamId));

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Scrims.class);
    }
}
