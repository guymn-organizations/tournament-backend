package tour.rov.profile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;

import tour.rov.profile.model.Image;
import tour.rov.profile.model.Match;
import tour.rov.profile.model.TeamInTournament;
import tour.rov.profile.model.Tournament;
import tour.rov.profile.repository.MatchRepo;
import tour.rov.profile.repository.TournamentRepo;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepo tournamentRepo;

    @Autowired
    private MatchRepo matchRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ImageService imageService;

    public Tournament findById(String id) {
        return tournamentRepo.findById(id).get();
    }

    public void saveTournament(Tournament tournament) {
        tournamentRepo.save(tournament);
    }

    public boolean existingTournament(String tournamentId) {
        return !tournamentRepo.existsById(tournamentId);
    }

    public void updateStatus(String id, Tournament updatetournament) {
        Tournament tournament = findById(id);

        tournament.setId(id);
        if (updatetournament.getStatus() != null) {
            tournament.setStatus(updatetournament.getStatus());
        }

        saveTournament(tournament);
    }

    public List<Match> createMatchesForAllTeams(String tournamentId) {
        // ดึงข้อมูลการแข่งขันโดยใช้ ID
        Tournament tournament = findById(tournamentId);

        // ดึงรายการทีมทั้งหมดในการแข่งขัน
        List<TeamInTournament> teams = tournament.getTeamJoin();

        // ตรวจสอบว่ามีอย่างน้อย 2 ทีมเพื่อสร้างแมทช์
        if (teams.size() < 2) {
            throw new RuntimeException("There aren't enough teams to make a match.");
        }

        List<Match> matches = new ArrayList<>();

        // สร้างแมทช์ให้กับทุกทีม
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                TeamInTournament team1 = teams.get(i);
                TeamInTournament team2 = teams.get(j);

                Match newMatch = new Match();
                newMatch.setTeamA(team1);
                newMatch.setTeamB(team2);

                // บันทึกแมทช์ใหม่ (หรือเก็บไว้ในรายการแมทช์)
                matchRepo.save(newMatch);

                matches.add(newMatch);
            }
        }

        // คืนรายการแมทช์ที่สร้างขึ้น
        return matches;
    }

    public Tournament findTournamentWithHighestReward() {
        // Create a query to find the tournament with the highest reward
        Query query = new Query().with(Sort.by(Sort.Order.desc("reward"))).limit(1);

        // Execute the query and retrieve the tournament with the highest reward
        return mongoTemplate.findOne(query, Tournament.class);
    }

    public void createTournament(Tournament tournament) {

        //เอารูป base64 จาก tournament มาสร้าง Image 
        Image image = new Image();
        image.setImageUrl(tournament.getImageTourUrl());
        imageService.saveImage(image);

        //เก็บ id รูปลง tour
        tournament.setImageTourUrl(image.getId());
        saveTournament(tournament);
    }
}