package tour.rov.profile.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id") // Maps to the same primary key as the parent Profile entity
public class ProfileGame extends Profile {

    @Column(name = "username")
    private String username;

    @Column(name = "openid")
    private String openid;

    public ProfileGame() {
    }

    public ProfileGame(Long id, String email, String first_name, String gender, String last_name, String password,
            Date birthday, String username, String openid) {
        super(id, email, first_name, gender, last_name, password, birthday);
        this.username = username;
        this.openid = openid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
