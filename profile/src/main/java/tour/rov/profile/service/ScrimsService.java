package tour.rov.profile.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Scrims;
import tour.rov.profile.repository.ScrimsRepo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;
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

    public void deleteScrimsPast(String teamId, LocalDateTime time) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("teamA.id").is(teamId),
                Criteria.where("teamB.id").is(teamId));

        criteria = criteria.and("startDate").lt(time);

        Query query = new Query(criteria);

        List<Scrims> scrimsToDelete = mongoTemplate.find(query, Scrims.class);

        // Delete the Scrims
        for (Scrims scrim : scrimsToDelete) {
            mongoTemplate.remove(scrim);
        }
    }

    public List<Scrims> findScrimsByTeamId(String teamId, int pageIndex, int pageSize) {
        LocalDateTime now = LocalDateTime.now();

        Criteria criteria = new Criteria().orOperator(
                Criteria.where("teamA.id").is(teamId),
                Criteria.where("teamB.id").is(teamId));

        deleteScrimsPast(teamId, now);

        criteria = criteria.and("startDate").gt(now);

        Query query = new Query(criteria).with(Sort.by(Sort.Order.asc("startDate"))).skip(pageIndex * pageSize)
                .limit(pageSize);

        return mongoTemplate.find(query, Scrims.class);
    }

    public List<Scrims> findScrimsByTeamIdNoOpponent(String teamId, int pageIndex, int pageSize) {
        LocalDateTime now = LocalDateTime.now();

        Criteria criteria = new Criteria().andOperator(
                Criteria.where("teamA.id").is(teamId),
                Criteria.where("teamB").is(null));

        deleteScrimsPast(teamId, now);

        criteria = criteria.and("startDate").gt(now);

        Query query = new Query(criteria).with(Sort.by(Sort.Order.asc("startDate"))).skip(pageIndex * pageSize)
                .limit(pageSize);

        return mongoTemplate.find(query, Scrims.class);
    }

    public String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y HH:mm");
        return localDateTime.format(formatter);
    }

    public void deleteScrims(String id) {
        scrimsRepo.deleteById(id);
    }
}
