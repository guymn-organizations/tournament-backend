package tour.rov.profile.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "team_post")
public class TeamPost {
    @Id
    private String id;

    @DBRef
    private Team team;

    private List<PositionType> positions;

    public TeamPost() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<PositionType> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionType> positions) {
        this.positions = positions;
    }

}
