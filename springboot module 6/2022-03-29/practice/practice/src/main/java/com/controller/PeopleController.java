package com.controller;

import com.domain.People;
import com.service.PeopleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("people")
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public Object getPeoples(@RequestParam(name = "orderAge", required = false, defaultValue = "asc") String sort) {
        return this.peopleService.findAll(sort);
    }

    @GetMapping("/{id}")
    public Object getPeople(@PathVariable("id") Long id) {
        return this.peopleService.findById(id);
    }

    @PostMapping
    public Object addPeople(@RequestBody People p) {
        if (p.getId() == null) return null;
        return this.peopleService.add(p);
    }

    @PatchMapping("/{id}")
    public Object updatePeople(@PathVariable("id") Long id, @RequestBody People p) {
        p.setId(id);
        return this.peopleService.update(p);
    }

    @DeleteMapping("/{id}")
    public Object deletePeople(@PathVariable("id") Long id) {
        return this.peopleService.deleteById(id);
    }
}
