package tour.rov.profile.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "team_post")
public class TeamPost {
    @DBRef
    private Team team;
    private List<Position> positions;

    public TeamPost() {
    }

    public TeamPost(Team team, List<Position> positions) {
        this.team = team;
        this.positions = positions;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

}
