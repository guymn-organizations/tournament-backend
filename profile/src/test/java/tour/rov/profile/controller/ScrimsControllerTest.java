package tour.rov.profile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tour.rov.profile.controller.ScrimsController;
import tour.rov.profile.model.Scrims;
import tour.rov.profile.model.Team;
import tour.rov.profile.service.ScrimsService;
import tour.rov.profile.service.TeamService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ScrimsControllerTest {

    @Mock
    private ScrimsService scrimsService;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private ScrimsController scrimsController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scrimsController).build();
    }

    @Test
    public void testCreateScrims() throws Exception {
        // Scrims scrims = new Scrims();
        // scrims.setId("1");

        // when(scrimsService.saveScrims(scrims)).thenReturn(scrims);

        // mockMvc.perform(post("/scrims/create")
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .content(asJsonString(scrims)))
        //         .andExpect(status().isCreated());
    }

    @Test
    public void testGetScrimsByTeam() throws Exception {
        // String teamId = "1";
        // when(scrimsService.findScrimsByTeamId(teamId, 0, 10)).thenReturn(/*Your list of Scrims*/);

        // mockMvc.perform(get("/scrims/" + teamId)
        //         .param("pageIndex", "0")
        //         .param("pageSize", "10"))
        //         .andExpect(status().isOk());
    }

    @Test
    public void testSetTeamB() throws Exception {
        // String scrimsId = "1";
        // String teamName = "TeamB";

        // Scrims scrims = new Scrims();
        // scrims.setId(scrimsId);

        // Team team = new Team();
        // team.setName(teamName);

        // when(scrimsService.exsitById(scrimsId)).thenReturn(true);
        // when(scrimsService.findScrimsById(scrimsId)).thenReturn(scrims);
        // when(teamService.findByName(teamName)).thenReturn(team);

        // mockMvc.perform(put("/scrims/" + scrimsId + "/add_teamB")
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .content(teamName))
        //         .andExpect(status().isOk());
    }

    @Test
    public void testDeleteScrims() throws Exception {
        // String scrimsId = "1";
        // when(scrimsService.deleteScrims(scrimsId)).thenReturn(true);

        // mockMvc.perform(delete("/scrims/" + scrimsId))
        //         .andExpect(status().isOk());
    }

    // Helper method to convert objects to JSON format
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
