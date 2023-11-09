package tour.rov.profile.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "team_in_tournament")
public class TeamInTournament {
    @Id
    private String id;

    @DBRef
    private Team team;

    private int win;

    public TeamInTournament() {
        this.win = 0;
    }

    public TeamInTournament(Team team, int win) {
        this.team = team;
        this.win = win;
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

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

}
