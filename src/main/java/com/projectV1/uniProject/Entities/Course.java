package com.projectV1.uniProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;
    private String department;
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<StudentEnrollCourse> studentEnrolled = new HashSet<StudentEnrollCourse>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<StudentEnrollCourse> getStudentEnrolled() {
        return studentEnrolled;
    }

    public void setStudentEnrolled(Set<StudentEnrollCourse> studentEnrolled) {
        this.studentEnrolled = studentEnrolled;
    }
}
