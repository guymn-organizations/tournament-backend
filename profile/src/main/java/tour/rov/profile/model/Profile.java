package tour.rov.profile.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
public class Profile {
    @Id
    private String id;

    private LocalDate birthday;

    @Indexed(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String password;

    private String imageProfileUrl;

    private ProfileGame profileGame;

    @DBRef
    private List<Message> messages;

    public Profile() {
    }

    public Profile(String id, LocalDate birthday, String email, String firstName, Gender gender, String lastName,
            String password, String imageProfileUrl, ProfileGame profileGame, List<Message> messages) {
        this.id = id;
        this.birthday = birthday;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.password = password;
        this.imageProfileUrl = imageProfileUrl;
        this.profileGame = profileGame;
        this.messages = messages;
    }

    public enum Gender {
        Male,
        Female
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageProfileUrl() {
        return imageProfileUrl;
    }

    public void setImageProfileUrl(String imageProfileUrl) {
        this.imageProfileUrl = imageProfileUrl;
    }

    public ProfileGame getProfileGame() {
        return profileGame;
    }

    public void setProfileGame(ProfileGame profileGame) {
        this.profileGame = profileGame;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
