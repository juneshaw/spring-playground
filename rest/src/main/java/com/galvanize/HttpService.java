package com.galvanize;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class HttpService {

    private final Config config;

    public HttpService(Config config) {
        this.config = config;
    }

    private static final RestTemplate restTemplate = new RestTemplate();

    public List<Movie> get(String query) throws Exception {
        URI uri = UriComponentsBuilder
                .fromUriString(config.getUrl())
                .buildAndExpand(query)
                .toUri();
        RequestEntity request = new RequestEntity(HttpMethod.GET, uri);
        ParameterizedTypeReference<List<Movie>> typeRef = new ParameterizedTypeReference<List<Movie>>() {
        };
        ResponseEntity<List<Movie>> response =
                restTemplate.exchange(
                        request,
                        typeRef);
        return response.getBody();
    }
}
