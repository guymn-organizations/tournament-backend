package tour.rov.profile.model;

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

    @DBRef
    private Profile DSL;

    @DBRef
    private Profile JG;

    @DBRef
    private Profile MID;

    @DBRef
    private Profile ADL;

    @DBRef
    private Profile SUP;

    @DBRef
    private List<Profile> teamReserve;

    // @DBRef
    // private List<Tournament> tournament;

    @DBRef
    private List<Message> messages;

    private String imageTeamUrl;

    public Team() {
    }

    public Team(String id, String name, Profile leader, Profile dSL, Profile jG, Profile mID, Profile aDL, Profile sUP,
            List<Profile> teamReserve, List<Message> messages, String imageTeamUrl) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.DSL = dSL;
        this.JG = jG;
        this.MID = mID;
        this.ADL = aDL;
        this.SUP = sUP;
        this.teamReserve = teamReserve;
        this.messages = messages;
        this.imageTeamUrl = imageTeamUrl;

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

    public Profile getLeader() {
        return leader;
    }

    public void setLeader(Profile leader) {
        this.leader = leader;
    }

    public Profile getDSL() {
        return DSL;
    }

    public void setDSL(Profile dSL) {
        DSL = dSL;
    }

    public Profile getJG() {
        return JG;
    }

    public void setJG(Profile jG) {
        JG = jG;
    }

    public Profile getMID() {
        return MID;
    }

    public void setMID(Profile mID) {
        MID = mID;
    }

    public Profile getADL() {
        return ADL;
    }

    public void setADL(Profile aDL) {
        ADL = aDL;
    }

    public Profile getSUP() {
        return SUP;
    }

    public void setSUP(Profile sUP) {
        SUP = sUP;
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

    
}
