package com.ojdgaf.examples.bootapp.repositories;

import com.ojdgaf.examples.bootapp.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByName(String name);
}
