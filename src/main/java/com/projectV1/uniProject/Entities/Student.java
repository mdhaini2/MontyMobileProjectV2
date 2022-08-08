package com.projectV1.uniProject.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
    private String fullName;
    private String dob;
    private double gpa;

    @OneToMany(mappedBy = "student")
    private Set<StudentEnrollCourse> enrolledCourses = new HashSet<StudentEnrollCourse>();

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Set<StudentEnrollCourse> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(Set<StudentEnrollCourse> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
