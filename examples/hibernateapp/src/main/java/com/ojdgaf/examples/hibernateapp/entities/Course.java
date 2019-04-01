package com.ojdgaf.examples.hibernateapp.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        getStudents().add(student);
        student.getCourses().add(this);
    }

    public void removeStudent(Student student) {
        getStudents().remove(student);
        student.getCourses().remove(this);
    }

    @Override
    public int hashCode() {
        return 11 * getId() * getName().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Course)) return false;

        Course c = (Course) o;

        return Objects.equals(getId(), c.getId()) &&
                Objects.equals(getName(), c.getName());
    }

    @Override
    public String toString() {
        return "Course: " + getId() + " -> '" + getName() + "'\n";
    }
}
