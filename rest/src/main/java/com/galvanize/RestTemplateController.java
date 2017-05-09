package com.galvanize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class RestTemplateController {

    @Autowired
    HttpService httpService;

    @Autowired
    Config config;

    @GetMapping
    public Movie getMovie(@RequestParam String query) throws Exception {
        return httpService.get(
                config.getUrl(),
                query);

    }
}
