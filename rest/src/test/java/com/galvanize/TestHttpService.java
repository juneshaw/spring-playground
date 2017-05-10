package com.galvanize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(properties= {"url = blah"})
public class TestHttpService {

//    @MockBean
    Config config;

    @Mock
    private RestTemplate template;

    HttpService httpService;

    @Before
    public void initialize() throws Exception {
        config = new Config();
        config.setUrl("http://www.omdbapi.com");
        httpService = new HttpService(config);
    }

    @Test
    public void testHttpServiceGet() throws Exception {
        List<Movie> movies = httpService.get("harry");

//
//        Mockito.verify(template, Mockito.times(1))
//                .exchange(Mockito.<RequestEntity> any(),
//                        Mockito.<Class<?>> any());
    }
}
