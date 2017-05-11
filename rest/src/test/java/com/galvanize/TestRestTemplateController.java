package com.galvanize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestTemplateController.class)
@TestPropertySource(properties = {
        "config.movies.url=url"
})
public class TestRestTemplateController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public HttpService httpService;

    Movie mockedMovie;

    @Before
    public void initialize() throws Exception {
        Gson gson = new GsonBuilder().create();
        mockedMovie = new Movie(
                "mockedTitle",
                "mockedImdbId",
                "mockedPoster",
                "mockedYear",
                "mockedType");
        String query = "test";
        String json = gson.toJson(mockedMovie);

        Mockito
                .when(httpService.get("help"))
                .thenReturn(Arrays.asList(mockedMovie));
    }

    @Test
    public void testRestTemplateController() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/movies?q=help");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(mockedMovie.getTitle())))
                .andExpect(jsonPath("$[0].imdbId", is(mockedMovie.getImdbId())))
                .andExpect(jsonPath("$[0].poster", is(mockedMovie.getPoster())))
                .andExpect(jsonPath("$[0].year", is(mockedMovie.getYear())))
                .andExpect(jsonPath("$[0].type", is(mockedMovie.getType())));
    }
}
