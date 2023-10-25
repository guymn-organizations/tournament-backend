package tour.rov.profile.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_post")
public class PlayerPost {
    @Id
    private String id;

    @DBRef
    private Profile profile;

    private List<Position> positions;

    public PlayerPost() {
    }

    public PlayerPost(String id, Profile profile, List<Position> positions) {
        this.id = id;
        this.profile = profile;
        this.positions = positions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

}