package com.galvanize;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class HttpService {

    private final Config config;
    private final RestTemplate restTemplate = new RestTemplate();

    public HttpService(Config config) {
        this.config = config;
    }

    public RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    public List<Movie> get(String query) throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(config.getUrl())
                .queryParam("s", query);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        RequestEntity request = new RequestEntity(headers, HttpMethod.GET, builder.build().toUri());
        ResponseEntity<ResponseWrapperMovie> response =
                restTemplate.exchange(
                        request,
                        ResponseWrapperMovie.class);
        return response.getBody().getSearch();
    }
}
