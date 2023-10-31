package tour.rov.profile.model;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "advert")
public class Advert {
    @Id
    private String id;

    private String imageAdvertUrl;
    private LocalTime time;
    private String linkAdvertUrl;

    public Advert(){}

    public Advert(String id, String imageAdvertUrl, LocalTime time, String linkAdvertUrl) {
        this.id = id;
        this.imageAdvertUrl = imageAdvertUrl;
        this.time = time;
        this.linkAdvertUrl = linkAdvertUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageAdvertUrl() {
        return imageAdvertUrl;
    }

    public void setImageAdvertUrl(String imageAdvertUrl) {
        this.imageAdvertUrl = imageAdvertUrl;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLinkAdvertUrl() {
        return linkAdvertUrl;
    }

    public void setLinkAdvertUrl(String linkAdvertUrl) {
        this.linkAdvertUrl = linkAdvertUrl;
    }
    
}
