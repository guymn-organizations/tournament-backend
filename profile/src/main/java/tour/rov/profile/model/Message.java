package tour.rov.profile.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class Message {
    @Id
    private String id;

    private String sender;

    private String scrimsId;

    private PositionType positionType;

    private MessageType messageType;

    private String content;

    private LocalDate sendDate;

    public Message(MessageType messageType) {
        this.sendDate = LocalDate.now();
        this.messageType = messageType;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public String getScrimsId() {
        return scrimsId;
    }

    public void setScrimsId(String scrimsId) {
        this.scrimsId = scrimsId;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

}
