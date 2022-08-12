package com.projectV1.uniProject.Exceptions;

import com.projectV1.uniProject.Entities.StudentEnrollCourse;

public class StudentEnrollException extends Exception{
    public StudentEnrollException(String error){
        super(error);
    }
}
