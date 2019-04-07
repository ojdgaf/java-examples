package com.ojdgaf.examples.bootapp.repositories;

import com.ojdgaf.examples.bootapp.entities.RecordBook;
import com.ojdgaf.examples.bootapp.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "record-books")
public interface RecordBookRepository extends JpaRepository<RecordBook, Integer> {
    RecordBook findBySerialNumber(String serialNumber);

    RecordBook findByStudent(Student student);
}
