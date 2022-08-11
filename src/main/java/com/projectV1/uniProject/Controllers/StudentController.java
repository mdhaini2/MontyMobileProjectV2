package com.projectV1.uniProject.Controllers;

import com.projectV1.uniProject.Entities.Student;
import com.projectV1.uniProject.Exceptions.StudentNotFoundException;
import com.projectV1.uniProject.Exceptions.UserNotFoundException;
import com.projectV1.uniProject.Services.StudentService;
import com.projectV1.uniProject.Utils.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Only Student can add Student
    @PostMapping(value = "/addStudent/{user_id}")
    public Response addStudent(@RequestBody Student student,@PathVariable int user_id) throws UserNotFoundException {
        return studentService.addStudent(student,user_id);
    }

    // Only Student can delete Student
    @DeleteMapping(value = "/deleteStudent")
    public Response deleteStudent(@RequestParam int id) throws StudentNotFoundException {
        return studentService.deleteStudent(id);
    }

    //Only Instructor can get All Students
    @GetMapping(value = "/getAllStudents")
    public Response getAllStudents(){
        return studentService.getAllStudents();
    }
}
