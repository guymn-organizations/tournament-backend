package tour.rov.profile.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Position {
    private PositionType positionType;

    private String positionName;

    @DBRef
    private Profile player;

    public Position() {
    }

    public Position(Profile player) {
        this.player = player;
    }

    public Position(PositionType positionType, String positionName, Profile player) {
        this.positionType = positionType;
        this.positionName = positionName;
        this.player = player;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionMame(String position_name) {
        this.positionName = position_name;
    }

    public Profile getPlayer() {
        return player;
    }

    public void setPlayer(Profile player) {
        this.player = player;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }
}
