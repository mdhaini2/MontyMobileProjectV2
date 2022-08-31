package com.projectV1.uniProject.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StudentEnrollCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String studentGrade;
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public StudentEnrollCourse(String studentGrade, String status, Student student, Course course) {
        this.studentGrade = studentGrade;
        this.status = status;
        this.student = student;
        this.course = course;
    }

    public StudentEnrollCourse() {
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentEnrollCourse{" +
                "id=" + id +
                ", studentGrade='" + studentGrade + '\'' +
                ", status='" + status + '\'' +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
