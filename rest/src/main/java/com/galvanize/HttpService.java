package com.galvanize;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class HttpService {

    private final Config config;

    public HttpService(Config config) {
        this.config = config;
    }

    private static final RestTemplate restTemplate = new RestTemplate();

    public List<Movie> get(String query) throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(config.getUrl())
                .queryParam("s", query);
        RequestEntity request = new RequestEntity(HttpMethod.GET, builder.build().toUri());
        ParameterizedTypeReference<Map<String, List<Movie>>> typeRef =
                new ParameterizedTypeReference<Map<String, List<Movie>>>() {};
        ResponseEntity<Map<String, List<Movie>>> response =
                restTemplate.exchange(
                        request,
                        typeRef);
        return response.getBody().get("Search");
    }
}
