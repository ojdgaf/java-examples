package com.ojdgaf.examples.bootapp.repositories;

import java.util.List;

import com.ojdgaf.examples.bootapp.entities.RecordBook;
import com.ojdgaf.examples.bootapp.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    Student findByRecordBook(RecordBook recordBook);
}
