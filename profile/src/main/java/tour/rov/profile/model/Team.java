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

    @DBRef
    private Profile leader;

    private List<Position> positions;

    @DBRef
    private List<Profile> teamReserve;

    @DBRef
    private List<Message> messages;

    private String imageTeamUrl;

    private List<String> tournamentId;

    public Team() {
        setPositions(new ArrayList<Position>());
        getPositions().add(new Position(PositionType.DSL, "DARK SLAYER LANE", null));
        getPositions().add(new Position(PositionType.JG, "JUNGLE", null));
        getPositions().add(new Position(PositionType.MID, "MID LANE", null));
        getPositions().add(new Position(PositionType.ADL, "ABYSSAL DRAGON LANE", null));
        getPositions().add(new Position(PositionType.SUP, "SUPPORT", null));
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

    public List<String> getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(List<String> tournamentId) {
        this.tournamentId = tournamentId;
    }

}
