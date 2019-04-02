package com.ojdgaf.examples.bootapp.repositories;

import com.ojdgaf.examples.bootapp.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "faculties")
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
