package com.projectV1.uniProject.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;
    private String department;
    @OneToMany(mappedBy = "course")
    private Set<StudentEnrollCourse> enrolledCourses = new HashSet<StudentEnrollCourse>();
}
