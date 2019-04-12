package com.ojdgaf.examples.bootapp.entities;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "record_books")
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
        return 17 * (getId() == null ? super.hashCode() : getId());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof RecordBook)) return false;

        return Objects.equals(getId(), ((RecordBook) o).getId());
    }

    @Override
    public String toString() {
        return "Record Book: " + getId() + " -> '" + getSerialNumber() + "\n";
    }
}

