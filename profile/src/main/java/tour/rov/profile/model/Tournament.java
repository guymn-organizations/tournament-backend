package tour.rov.profile.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "tournament")
public class Tournament {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String detail;
    private Double reward;

    private LocalDate startRegisterDate;
    private LocalDate endRegisterDate;
    private LocalDate startTourDate;
    //private String imageTourUrl;
    private TournamenType tournamenType;
    
    @DBRef
    private int BOqualifyingRound;

    private int BOfinalRound;
    private List<TeamInTournament> teamJoin;
    private Status status;

    @DBRef
    private List<Match> matchList;

    public Tournament(){}

    public Tournament(String id, String name, String detail, Double reward, LocalDate startRegisterDate,
            LocalDate endRegisterDate, LocalDate startTourDate, int bOqualifyingRound, int bOfinalRound,
            List<TeamInTournament> teamJoin, Status status, List<Match> matchList) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.reward = reward;
        this.startRegisterDate = startRegisterDate;
        this.endRegisterDate = endRegisterDate;
        this.startTourDate = startTourDate;
        BOqualifyingRound = bOqualifyingRound;
        BOfinalRound = bOfinalRound;
        this.teamJoin = teamJoin;
        this.status = status;
        this.matchList = matchList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public LocalDate getStartRegisterDate() {
        return startRegisterDate;
    }

    public void setStartRegisterDate(LocalDate startRegisterDate) {
        this.startRegisterDate = startRegisterDate;
    }

    public LocalDate getEndRegisterDate() {
        return endRegisterDate;
    }

    public void setEndRegisterDate(LocalDate endRegisterDate) {
        this.endRegisterDate = endRegisterDate;
    }

    public LocalDate getStartTourDate() {
        return startTourDate;
    }

    public void setStartTourDate(LocalDate startTourDate) {
        this.startTourDate = startTourDate;
    }

    public int getBOqualifyingRound() {
        return BOqualifyingRound;
    }

    public void setBOqualifyingRound(int bOqualifyingRound) {
        BOqualifyingRound = bOqualifyingRound;
    }

    public int getBOfinalRound() {
        return BOfinalRound;
    }

    public void setBOfinalRound(int bOfinalRound) {
        BOfinalRound = bOfinalRound;
    }

    public List<TeamInTournament> getTeamJoin() {
        return teamJoin;
    }

    public void setTeamJoin(List<TeamInTournament> teamJoin) {
        this.teamJoin = teamJoin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public TournamenType getTournamenType() {
        return tournamenType;
    }

    public void setTournamenType(TournamenType tournamenType) {
        this.tournamenType = tournamenType;
    }
    
}
