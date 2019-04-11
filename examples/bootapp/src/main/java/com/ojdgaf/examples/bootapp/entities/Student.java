package com.ojdgaf.examples.bootapp.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id ASC")
    private List<Phone> phones = new ArrayList<>();

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

    @Override
    public int hashCode() {
        return 22 * (getId() == null ? super.hashCode() : getId());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Student)) return false;

        return Objects.equals(getId(), ((Student) o).getId());
    }

    @Override
    public String toString() {
        return "Student: " + getId() + " -> '" + getFirstName() + "' '" + getLastName();
    }
}
