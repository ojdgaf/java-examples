package com.ojdgaf.examples.bootapp.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

@Entity
@Table(name = "students")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Student.class)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private RecordBook recordBook;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id ASC")
    private List<Phone> phones = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    @OrderBy(value = "id ASC")
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RecordBook getRecordBook() {
        return recordBook;
    }

    public void setRecordBook(RecordBook recordBook) {
        this.recordBook = recordBook;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        getPhones().add(phone);
        phone.setStudent(this);
    }

    public void removePhone(Phone phone) {
        getPhones().remove(phone);
        phone.setStudent(null);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        getCourses().add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course) {
        getCourses().remove(course);
        course.getStudents().remove(this);
    }

    @Override
    public int hashCode() {
        return 22 * getId() * getFirstName().hashCode() * getLastName().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Student)) return false;

        Student s = (Student) o;

        return Objects.equals(getId(), s.getId()) &&
                Objects.equals(getFirstName(), s.getFirstName()) &&
                Objects.equals(getLastName(), s.getLastName());
    }

    @Override
    public String toString() {
        return "Student: " + getId() + " -> '" + getFirstName() + "' '" + getLastName();
    }
}
