package tour.rov.profile.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class ProfileGame {
    @Indexed(unique = true)
    private String name;

    @Indexed(unique = true)
    private String openId;

    @DBRef
    private Team myTeam;

    private String imageGameUrl;

    public ProfileGame() {
    }

    public ProfileGame(String name, String openId, Team myTeam, String imageGameUrl) {
        this.name = name;
        this.openId = openId;
        this.myTeam = myTeam;
        this.imageGameUrl = imageGameUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Team getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
    }

    public String getImageGameUrl() {
        return imageGameUrl;
    }

    public void setImageGameUrl(String imageGameUrl) {
        this.imageGameUrl = imageGameUrl;
    }

}
