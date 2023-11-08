package tour.rov.profile.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private String imageTourUrl;

    private TournamenType tournamenType;

    private int bOqualifyingRound;

    private int bOfinalRound;

    @DBRef
    private List<TeamInTournament> teamJoin;

    private Status status ;

    @DBRef
    private List<Match> matchList;

    private String payments;

    private int numberOfTeam;

    public enum TournamenType {
        Free,
        Paid
    }

    public enum Status {
        รอดำเนินการ, 
        เปิดรับสมัคร, 
        ปิดรับสมัคร, 
        กำลังแข่งขัน, 
        จบการแข่งขัน
    }
    
    public Tournament() {
        this.teamJoin = new ArrayList<>();
        this.status = Status.รอดำเนินการ;
    }

    public Tournament(String name, String detail, Double reward, LocalDate startRegisterDate,
            LocalDate endRegisterDate, LocalDate startTourDate, String imageTourUrl, TournamenType tournamenType,
            int bOqualifyingRound, int bOfinalRound, List<TeamInTournament> teamJoin, Status status,
            List<Match> matchList, String payments, int numberOfTeam) {
        this.name = name;
        this.detail = detail;
        this.reward = reward;
        this.startRegisterDate = startRegisterDate;
        this.endRegisterDate = endRegisterDate;
        this.startTourDate = startTourDate;
        this.imageTourUrl = imageTourUrl;
        this.tournamenType = tournamenType;
        this.bOqualifyingRound = bOqualifyingRound;
        this.bOfinalRound = bOfinalRound;
        this.teamJoin = teamJoin;
        this.status = status;
        this.matchList = matchList;
        this.payments = payments;
        this.numberOfTeam = numberOfTeam;
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

    public String getImageTourUrl() {
        return imageTourUrl;
    }

    public void setImageTourUrl(String imageTourUrl) {
        this.imageTourUrl = imageTourUrl;
    }

    public TournamenType getTournamenType() {
        return tournamenType;
    }

    public void setTournamenType(TournamenType tournamenType) {
        this.tournamenType = tournamenType;
    }

    public int getbOqualifyingRound() {
        return bOqualifyingRound;
    }

    public void setbOqualifyingRound(int bOqualifyingRound) {
        this.bOqualifyingRound = bOqualifyingRound;
    }

    public int getbOfinalRound() {
        return bOfinalRound;
    }

    public void setbOfinalRound(int bOfinalRound) {
        this.bOfinalRound = bOfinalRound;
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

    public int getNumberOfTeam() {
        return numberOfTeam;
    }

    public void setNumberOfTeam(int numberOfTeam) {
        this.numberOfTeam = numberOfTeam;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

   
}
