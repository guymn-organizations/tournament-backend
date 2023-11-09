package tour.rov.profile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import tour.rov.profile.model.Image;
import tour.rov.profile.model.Profile;
import tour.rov.profile.model.Tournament;
import tour.rov.profile.model.Tournament.Status;
import tour.rov.profile.repository.TournamentRepo;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepo tournamentRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private MatchService matchService;

    public Tournament findById(String id) {
        return tournamentRepo.findById(id).get();
    }

    public void saveTournament(Tournament tournament) {
        tournamentRepo.save(tournament);
    }

    public boolean existingTournament(String tournamentId) {
        return !tournamentRepo.existsById(tournamentId);
    }

    public void updateStatus(String id, Tournament updateStatus) {
        Tournament tournament = findById(id);

        tournament.setId(id);
        if (updateStatus.getStatus() != null) {
            tournament.setStatus(updateStatus.getStatus());
        }

        saveTournament(tournament);
    }

    public Tournament findTournamentWithHighestReward() {
        // Create a query to find the tournament with the highest reward
        Query query = new Query().with(Sort.by(Sort.Order.desc("reward"))).limit(1);

        // Execute the query and retrieve the tournament with the highest reward
        return mongoTemplate.findOne(query, Tournament.class);
    }

    public void createTournament(Tournament tournament, String profileId) {

        if (tournament.getImageTourUrl().length() > 0) {
            // เอารูป base64 จาก tournament มาสร้าง Image
            Image image = new Image();
            image.setImageUrl(tournament.getImageTourUrl());
            imageService.saveImage(image);
            // เก็บ id รูปลง tour
            tournament.setImageTourUrl(image.getId());
        }

        Profile creater = profileService.findById(profileId);

        tournament.setCreateer(creater);
        tournament.setStartDateRegister(LocalDate.now());
        tournament.setTeamJoin(new ArrayList<>());

        int bo = tournament.getBO();
        LocalDate date = tournament.getStartDateMatch();
        int num = tournament.getMaxNumberTeam();
        List<String> matchIdList = matchService.generateMatches(bo, date, num);
        tournament.setMatchList(matchIdList);
        tournament.setStatus(Status.Register);

        saveTournament(tournament);
    }

    public List<Tournament> getAllTournaments(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        List<Tournament> tournaments = tournamentRepo.findAllBy(pageable);
        return tournaments;
    }

}