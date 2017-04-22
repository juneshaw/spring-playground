package com.db;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonsController {
    private LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    public LessonRepository getRepository() {
        return repository;
    }

    public void setRepository(LessonRepository repository) {
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
}
