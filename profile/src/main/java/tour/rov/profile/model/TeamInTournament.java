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

    private int score;
    private int win;
    private int lose;

    public TeamInTournament() {
    }

    public TeamInTournament(Team team, int score, int win, int lose) {
        this.team = team;
        this.score = score;
        this.win = win;
        this.lose = lose;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
