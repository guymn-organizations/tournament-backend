package tour.rov.profile.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scrims")
public class Scrims {
    @Id
    private String id;

    @DBRef
    private Team teamA;

    @DBRef
    private Team teamB;

    private LocalDate startDate;

    @DBRef
    private List<Chat> chat;

    public Scrims(){}

    public Scrims(Team teamA, Team teamB, LocalDate startDate, List<Chat> chat) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.startDate = startDate;
        this.chat = chat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Chat> getChat() {
        return chat;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }
    

}
