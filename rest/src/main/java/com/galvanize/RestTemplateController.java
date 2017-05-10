package com.galvanize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class RestTemplateController {


    @Autowired
    HttpService httpService;


    @GetMapping
    public List<Movie> getMovie(@RequestParam String q) throws Exception {
        return httpService.get(q);
    }
}
