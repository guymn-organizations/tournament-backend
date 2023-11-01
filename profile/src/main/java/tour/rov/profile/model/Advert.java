package tour.rov.profile.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "advert")
public class Advert {
    @Id
    private String id;

    private String imageAdvertUrl;
    private String linkAdvertUrl;

    public Advert(){}

    public Advert(String id, String imageAdvertUrl, String linkAdvertUrl) {
        this.id = id;
        this.imageAdvertUrl = imageAdvertUrl;
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

    public String getLinkAdvertUrl() {
        return linkAdvertUrl;
    }

    public void setLinkAdvertUrl(String linkAdvertUrl) {
        this.linkAdvertUrl = linkAdvertUrl;
    }
    
}
