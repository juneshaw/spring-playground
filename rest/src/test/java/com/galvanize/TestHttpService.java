package com.galvanize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(properties= {"url = blah"})
public class TestHttpService {

    @Mock
    RestTemplate mockRestTemplate;

    HttpService httpService;

    Config config;

    @Before
    public void initialize() throws Exception {

    }

    @Test
    public void testHttpServiceGet() throws Exception {
        config = new Config();
        config.setUrl("http://www.omdbapi.com");
        httpService = new HttpService(config);
        mockRestTemplate = new RestTemplate();
        ReflectionTestUtils.setField(
                httpService,
                "restTemplate",
                mockRestTemplate);
        Movie movie = new Movie("MyTitle", "MyYear", "MyImdbId", "MyType", "MyPoster");
        ResponseWrapperMovie responseMovieWrapper = new ResponseWrapperMovie(Arrays.asList(movie), 99, "MyResponse");
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(mockRestTemplate);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(responseMovieWrapper);
        mockServer.expect(
                requestTo("http://www.omdbapi.com?s=harry"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        List<Movie> movies = httpService.get("harry");

        assertThat(movies.get(0).getTitle(), equalTo(movie.getTitle()));
        assertThat(movies.get(0).getYear(), equalTo(movie.getYear()));
        assertThat(movies.get(0).getImdbId(), equalTo(movie.getImdbId()));
        assertThat(movies.get(0).getType(), equalTo(movie.getType()));
        assertThat(movies.get(0).getPoster(), equalTo(movie.getPoster()));

    }
}
