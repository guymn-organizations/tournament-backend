package tour.rov.profile.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "match")
public class Match {
    @Id
    private String id;

    @DBRef
    private TeamInTournament teamA;

    @DBRef
    private TeamInTournament teamB;

    private LocalDate startDate;
    private List<String> report;

    @DBRef
    private List<Chat> chat;

    private int[] result = {0, 0};

    private String round;

    public Match(){}

    public Match(String round, TeamInTournament teamA, TeamInTournament teamB) {
        this.round = round;
        this.teamA = teamA;
        this.teamB = teamB;
    }
    
    public Match(TeamInTournament teamA, TeamInTournament teamB, LocalDate startDate, List<String> report, List<Chat> chat, int[] result) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.startDate = startDate;
        this.report = report;
        this.chat = chat;
        this.result = result;
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
    public TeamInTournament getTeamB() {
        return teamB;
    }
    public void setTeamB(TeamInTournament teamB) {
        this.teamB = teamB;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public List<String> getReport() {
        return report;
    }
    public void setReport(List<String> report) {
        this.report = report;
    }
    public List<Chat> getChat() {
        return chat;
    }
    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }
    public int[] getResult() {
        return result;
    }
    public void setResult(int[] result) {
        this.result = result;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
    
}
