package tour.rov.profile.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class Chat {
    @Id
    private String id;
    
    private String sender;
    private String content;
    private LocalDate timeSend;

    public Chat(){}
    
    public Chat(String sender, String content, LocalDate timeSend) {
        this.sender = sender;
        this.content = content;
        this.timeSend = timeSend;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDate getTimeSend() {
        return timeSend;
    }
    public void setTimeSend(LocalDate timeSend) {
        this.timeSend = timeSend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
