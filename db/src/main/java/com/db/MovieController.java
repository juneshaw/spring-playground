package com.db;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Movie> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Movie create(@RequestBody Movie Movie) {
        return this.repository.save(Movie);
    }

    @GetMapping("/{id}")
    public Movie read(@PathVariable Long id) {
        return this.repository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
    public Movie patch(@PathVariable Long id, @RequestBody Movie Movie) {
        return this.repository.save(Movie);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.repository.delete(id);
    }

    @GetMapping("/count")
    public Long count() {
        return this.repository.count();
    }

}
