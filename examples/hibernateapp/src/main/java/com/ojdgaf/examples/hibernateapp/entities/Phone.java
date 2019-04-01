package com.ojdgaf.examples.hibernateapp.entities;

import java.util.Objects;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @Column
    private String number;

    public Phone() {
    }

    public Phone(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        return 13 * getId() * getNumber().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Phone)) return false;

        Phone p = (Phone) o;

        return Objects.equals(getId(), p.getId()) &&
                Objects.equals(getNumber(), p.getNumber());
    }

    @Override
    public String toString() {
        return "Phone: " + getId() + " -> '" + getNumber();
    }
}
