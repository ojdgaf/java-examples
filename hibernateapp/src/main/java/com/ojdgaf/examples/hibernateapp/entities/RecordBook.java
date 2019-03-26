package com.ojdgaf.examples.hibernateapp.entities;

import java.util.Objects;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

@Entity
@Table(name = "record_books")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = RecordBook.class)
public class RecordBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial_number")
    private String serialNumber;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public RecordBook() {
    }

    public RecordBook(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        return 17 * getId() * getSerialNumber().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof RecordBook)) return false;

        RecordBook rb = (RecordBook) o;

        return Objects.equals(getId(), rb.getId()) &&
                Objects.equals(getSerialNumber(), rb.getSerialNumber());
    }

    @Override
    public String toString() {
        return "Record Book: " + getId() + " -> '" + getSerialNumber() + "\n";
    }
}
