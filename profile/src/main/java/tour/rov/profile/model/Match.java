package tour.rov.profile.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "match")
public class Match {
    @Id
    private String id;

    @DBRef
    private TeamInTournament teamA;

    private int[] resultA = { 0, 0 };

    @DBRef
    private TeamInTournament teamB;

    private int[] resultB = { 0, 0 };

    private LocalDate startDate;

    private int round;

    private String nextMatch;

    public Match() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TeamInTournament getTeamA() {
        return teamA;
    }

    public void setTeamA(TeamInTournament teamA) {
        this.teamA = teamA;
    }

    public int[] getResultA() {
        return resultA;
    }

    public void setResultA(int[] resultA) {
        this.resultA = resultA;
    }

    public TeamInTournament getTeamB() {
        return teamB;
    }

    public void setTeamB(TeamInTournament teamB) {
        this.teamB = teamB;
    }

    public int[] getResultB() {
        return resultB;
    }

    public void setResultB(int[] resultB) {
        this.resultB = resultB;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getNextMatch() {
        return nextMatch;
    }

    public void setNextMatch(String nextMatch) {
        this.nextMatch = nextMatch;
    }

}
