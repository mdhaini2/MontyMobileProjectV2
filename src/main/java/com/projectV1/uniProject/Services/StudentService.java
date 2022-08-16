package com.projectV1.uniProject.Services;

import com.projectV1.uniProject.Entities.Student;
import com.projectV1.uniProject.Entities.StudentEnrollCourse;
import com.projectV1.uniProject.Entities.Users;
import com.projectV1.uniProject.Exceptions.StudentNotFoundException;
import com.projectV1.uniProject.Exceptions.UserNotFoundException;
import com.projectV1.uniProject.Repositories.StudentRepository;
import com.projectV1.uniProject.Repositories.UserRepository;
import com.projectV1.uniProject.Utils.Response;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Log4j2
@AllArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    public Response addStudent(Student student, int id) throws UserNotFoundException {
        Users user;
        try {
            user = userRepository.findById(id).get();
            student.setUser(user);
            studentRepository.save(student);
            Response response = new Response(200, "Student added successfully", student);
            return response;
        } catch (Exception e) {
            throw new UserNotFoundException("addStudent: User with id: " + id + " not found");

        }



    }

    public Response deleteStudent(int id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id).get();
        if (student == null) {
            log.error("deleteStudent: Student not found");
            throw new StudentNotFoundException("deleteStudent: Student not found");
        }

        Set<StudentEnrollCourse> studentEnrollCourses = student.getEnrolledCourses();
        for (StudentEnrollCourse enrollCourse : studentEnrollCourses) {
            if (enrollCourse.getStatus().equalsIgnoreCase("Enrolled")) {
                Response response = new Response(403, "Can't delete student still enrolled in a course", enrollCourse);
                return response;
            }
        }

        studentRepository.deleteById(id);
        Response response = new Response(200, "Student Deleted Successfully", null);
        return response;
    }


    public Response getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        if (studentList.isEmpty()) {
            Response response = new Response(404, "Students not found", studentList);
            return response;
        }
        Response response = new Response(200, "Students retrieved successfully", studentList);
        return response;
    }

    public Response getEnrolledCourses(int studentId) {

        Student student = studentRepository.findById(studentId).get();
        Set<StudentEnrollCourse> studentEnrollCourses = student.getEnrolledCourses();

        Response response = new Response(200,"Enrolled courses for student"+ student.getFullName()+" retrieved!",studentEnrollCourses);
        return response;
    }
}
