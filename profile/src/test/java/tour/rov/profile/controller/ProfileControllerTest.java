package tour.rov.profile.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import tour.rov.profile.model.Profile;
import tour.rov.profile.service.ProfileService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doReturn;

public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterProfile() {
        // Profile profile = new Profile();
        // profile.setEmail("test@example.com");
        // profile.setPassword("password");

        // // Mock the behavior of the profileService
        // doReturn(false).when(profileService).existingProfileByEmail("test@example.com");
        // doReturn(profile).when(profileService).saveProfile(profile);

        // // Call the method to test
        // ResponseEntity<?> response = profileController.register(profile);

        // // Verify the interactions
        // verify(profileService, times(1)).existingProfileByEmail("test@example.com");
        // verify(profileService, times(1)).saveProfile(profile);

        // // Assert the result
        // // You can assert the response status, content, or other properties here
    }

    // Add more test methods for other controller actions

}
