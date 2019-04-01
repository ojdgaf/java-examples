package com.ojdgaf.examples.springapp.services;

import com.ojdgaf.examples.springapp.entities.Faculty;
import com.ojdgaf.examples.springapp.repositories.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class FacultyService implements Service<Faculty> {
    private Repository<Faculty> repository;

    public FacultyService(Repository<Faculty> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Faculty> all() {
        return repository.all();
    }

    @Override
    @Transactional
    public Faculty get(Serializable id) {
        return repository.get(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Faculty faculty) {
        repository.saveOrUpdate(faculty);
    }

    @Override
    @Transactional
    public void delete(Faculty faculty) {
        repository.delete(faculty);
    }
}
