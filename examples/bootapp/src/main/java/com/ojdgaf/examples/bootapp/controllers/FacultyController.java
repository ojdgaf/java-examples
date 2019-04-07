package com.ojdgaf.examples.bootapp.controllers;

import com.ojdgaf.examples.bootapp.entities.Faculty;
import com.ojdgaf.examples.bootapp.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/faculties")
public class FacultyController {
    @Autowired
    private FacultyRepository repository;

    @GetMapping("name/{name}")
    public Faculty findByName(@PathVariable String name) {
        return repository.findByName(name);
    }
}
