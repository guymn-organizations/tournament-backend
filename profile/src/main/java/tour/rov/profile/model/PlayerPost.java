package tour.rov.profile.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_post")
public class PlayerPost {
    @DBRef
    private Profile profile;
    private List<Position> positions;

    public PlayerPost() {
    }

    public PlayerPost(Profile profile, List<Position> positions) {
        this.profile = profile;
        this.positions = positions;
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
