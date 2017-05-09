package com.galvanize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestTemplateController.class)
@ContextConfiguration
//@SpringBootTest
//@TestPropertySource(properties = {
//        "config.movies.url=url"
//})
public class TestRestTemplateController {
//
//    @Autowired
//    Config config;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public HttpService httpService;

    Movie mockedMovie;

    @Before
    public void initialize() throws Exception {
        mockedMovie = new Movie(
                "mockedTitle",
                "mockedImdbId",
                "mockedPoster",
                "mockedYear");
        String query = "test";

        Mockito
                .when(httpService.get("help"))
                .thenReturn(mockedMovie);
    }

    @Test
    public void testRestTemplateController() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/movies?q=help");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$0.title", is(mockedMovie.getTitle())))
                .andExpect(jsonPath("$0.imdbId", is(mockedMovie.getImdbId())))
                .andExpect(jsonPath("$0.poster", is(mockedMovie.getPoster())))
                .andExpect(jsonPath("$0.year", is(mockedMovie.getYear())));
    }
}
