package com.galvanize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(properties={"movies.url=http://www.omdbapi.com"})
public class TestHttpService {

    private HttpService httpService;
    private MockRestServiceServer mockServer;
    private Config config;
    private String url = "http://omdbapi.com/";

    @Before
    public void initialize() {
        config = mock(Config.class);
        when (config.getUrl()).thenReturn(url);

        httpService = new HttpService(config);
        this.mockServer = MockRestServiceServer.createServer(httpService.getRestTemplate());
    }

    @Test
    public void testHttpServiceGet() throws Exception {
        Movie movie = new Movie("MyTitle", "MyYear", "MyImdbId", "MyType", "MyPoster");
        ResponseWrapperMovie responseWrapperMovie = new ResponseWrapperMovie(Arrays.asList(movie), 99, "MyResponse");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapperMovie);
        mockServer.expect(
                requestTo(url + "?s=harry"))
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
