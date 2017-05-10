package com.galvanize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class RestTemplateController {

    private final HttpService httpService;

    public RestTemplateController(HttpService httpService) {
        this.httpService = httpService;
    }

    @GetMapping
    public List<Movie> getMovie(@RequestParam String q) throws Exception {
        return httpService.get(q);
    }
}
