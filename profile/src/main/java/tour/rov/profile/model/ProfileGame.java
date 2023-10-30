package tour.rov.profile.model;

import org.springframework.data.mongodb.core.index.Indexed;

public class ProfileGame {
    @Indexed(unique = true)
    private String name;

    @Indexed(unique = true)
    private String openId;

    private String myTeam;

    private String imageGameUrl;

    public ProfileGame() {
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

    public String getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(String myTeam) {
        this.myTeam = myTeam;
    }

    public String getImageGameUrl() {
        return imageGameUrl;
    }

    public void setImageGameUrl(String imageGameUrl) {
        this.imageGameUrl = imageGameUrl;
    }

}
