package tour.rov.profile.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tournament")
public class Tournament {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String detail;

    private Double reward;

    private Double fee;

    private LocalDate startDateRegister;

    private LocalDate startDateMatch;

    private String imageTourUrl;

    private static int BO;

    private List<String> teamJoin;

    private Status status;

    private List<String> matchList;

    private int maxNumberTeam;

    private Profile createer;

    public enum Status {
        Register,
        Competing,
        End
    }

    public Tournament() {
        this.teamJoin = new ArrayList<>();
        this.matchList = new ArrayList<>();
        this.status = Status.Register;
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

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public LocalDate getStartDateRegister() {
        return startDateRegister;
    }

    public void setStartDateRegister(LocalDate startDateRegister) {
        this.startDateRegister = startDateRegister;
    }

    public LocalDate getStartDateMatch() {
        return startDateMatch;
    }

    public void setStartDateMatch(LocalDate startDateMatch) {
        this.startDateMatch = startDateMatch;
    }

    public String getImageTourUrl() {
        return imageTourUrl;
    }

    public void setImageTourUrl(String imageTourUrl) {
        this.imageTourUrl = imageTourUrl;
    }

    public static int getBO() {
        return BO;
    }

    public void setBO(int bO) {
        BO = bO;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<String> matchList) {
        this.matchList = matchList;
    }

    public int getMaxNumberTeam() {
        return maxNumberTeam;
    }

    public void setMaxNumberTeam(int maxNumberTeam) {
        this.maxNumberTeam = maxNumberTeam;
    }

    public Profile getCreateer() {
        return createer;
    }

    public void setCreateer(Profile createer) {
        this.createer = createer;
    }

    public List<String> getTeamJoin() {
        return teamJoin;
    }

    public void setTeamJoin(List<String> teamJoin) {
        this.teamJoin = teamJoin;
    }

}