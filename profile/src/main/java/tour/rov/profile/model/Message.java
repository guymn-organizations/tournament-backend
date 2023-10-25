package tour.rov.profile.model;

public class Message {
    private String sender;
    private MessageType messageType;
    private String content;

    public Message(String sender, MessageType class1, String content) {
        this.sender = sender;
        this.messageType = class1;
        this.content = content;
    }

    public enum MessageType {
        INVITE_TO_JOIN_TEAM,
        REQUEST_TO_JOIN_TEAM,
        INVITE_TO_SCRIMS,
        SYSTEM_ALERT
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
