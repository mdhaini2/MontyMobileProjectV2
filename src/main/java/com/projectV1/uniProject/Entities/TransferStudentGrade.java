package com.projectV1.uniProject.Entities;

import java.io.Serializable;

public class TransferStudentGrade implements Serializable {
    private String courseName;
    private String studentName;
    private String studentGrade;
    private String courseStatus;

    public TransferStudentGrade(String courseName, String studentName, String studentGrade, String courseStatus) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
        this.courseStatus = courseStatus;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
}
