package tour.rov.profile.model;

public class Position {
    private PositionType positionType;
    private String position_name;
    private Profile player;

    public Position() {
    }

    public Position(PositionType positionType, String position_name, Profile player) {
        this.positionType = positionType;
        this.position_name = position_name;
        this.player = player;
    }

    public enum PositionType {
        DSL, JG, MID, ADL, SUP
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
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
