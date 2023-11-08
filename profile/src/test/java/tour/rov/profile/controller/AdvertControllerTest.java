package tour.rov.profile.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tour.rov.profile.model.Advert;
import tour.rov.profile.service.AdvertService;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(AdvertController.class)
public class AdvertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdvertService advertService;

    @Test
    public void testGetAllAdvert() throws Exception {
        // Arrange
        List<Advert> adverts = new ArrayList<>();
        adverts.add(new Advert("image1.jpg", "link1"));
        adverts.add(new Advert("image2.jpg", "link2"));

        Mockito.when(advertService.getAllAdverts()).thenReturn(adverts);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/advert")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].imageAdvertUrl").value("image1.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].imageAdvertUrl").value("image2.jpg"));
    }

    @Test
    public void testCreateAdvert() throws Exception {
        // Arrange
        Advert newAdvert = new Advert("newImage.jpg", "newLink");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/advert/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"imageAdvertUrl\": \"newImage.jpg\", \"linkAdvertUrl\": \"newLink\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Advertisement was created\n" + newAdvert));
    }
}
