package com.db;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository repository;

    public LessonController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Lesson read(@PathVariable Long id) {
        return this.repository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
    public Lesson patch(@PathVariable Long id, @RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.repository.delete(id);
    }

}
