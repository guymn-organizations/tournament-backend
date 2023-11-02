package tour.rov.profile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Profile;
import tour.rov.profile.model.ProfileGame;
import tour.rov.profile.model.Profile.Gender;

@SpringBootTest
public class ProfileControllerTest {

    @Autowired
    private ProfileController profileController;

    @Test
    public void testRegister() {
        Profile profile = new Profile(); // profile to test
        LocalDate myDateObj = LocalDate.of(2004, 4, 28);
        profile.setId("testId");
        profile.setFirstName("Nattawut");
        profile.setLastName("Pandonthong");
        profile.setPassword("whuirofweishf");
        profile.setBirthday(myDateObj);
        profile.setGender(Gender.Male);
        profile.setEmail("tournament@gmail.com");

        ResponseEntity<?> response = profileController.register(profile);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testLogin() {
        ResponseEntity<?> response = profileController.login("tournament@gmail.com", "whuirofweishf");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEditProfile() {
        Profile profileToUpdate = new Profile();
        profileToUpdate.setLastName("pan");
        ResponseEntity<?> response = profileController.editProfile("testId", profileToUpdate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetMessages() {
        ResponseEntity<?> response = profileController.getMessages("testId");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
