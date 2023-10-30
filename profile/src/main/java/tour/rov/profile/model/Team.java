package tour.rov.profile.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "team")
public class Team {
    @Id
    private String id;

    private String name;

    private Profile leader;

    private List<Position> positions = new ArrayList<>();

    @DBRef
    private List<Profile> teamReserve = new ArrayList<>();

    @DBRef
    private List<Message> messages = new ArrayList<>();

    private String imageTeamUrl;

    // @DBRef
    // private List<Tournament> tournament;

    public Team() {
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

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<Profile> getTeamReserve() {
        return teamReserve;
    }

    public void setTeamReserve(List<Profile> teamReserve) {
        this.teamReserve = teamReserve;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getImageTeamUrl() {
        return imageTeamUrl;
    }

    public void setImageTeamUrl(String imageTeamUrl) {
        this.imageTeamUrl = imageTeamUrl;
    }

    public Profile getLeader() {
        return leader;
    }

    public void setLeader(Profile leader) {
        this.leader = leader;
    }

}
