package com.galvanize;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class HttpService {
//
//    @Autowired
//    Config config;

//    @Autowired
//    RestTemplate restTemplate;
    private static final RestTemplate restTemplate = new RestTemplate();

    public static Movie get(String url, String query) throws Exception {
        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .buildAndExpand(query)
                .toUri();
        RequestEntity request = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Movie> response = restTemplate.exchange(request, Movie.class);
        return response.getBody();
    }
}
