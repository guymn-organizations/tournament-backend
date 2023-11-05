package tour.rov.profile.model;

import org.springframework.data.annotation.Id;

public class payment {
    @Id
    private String id;

    private String qrCode;

    public payment(){}

    public payment(String id, String qrCode) {
        this.id = id;
        this.qrCode = qrCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    
}
