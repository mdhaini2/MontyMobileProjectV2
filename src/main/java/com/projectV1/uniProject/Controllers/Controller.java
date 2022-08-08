package com.projectV1.uniProject.Controllers;

import com.projectV1.uniProject.Entities.StudentEnrollCourse;
import com.projectV1.uniProject.Entities.Users;
import com.projectV1.uniProject.Exceptions.RoleInvalidException;
import com.projectV1.uniProject.Services.InstructorService;
import com.projectV1.uniProject.Services.StudentEnrollCourseService;
import com.projectV1.uniProject.Services.StudentService;
import com.projectV1.uniProject.Services.UserService;
import com.projectV1.uniProject.Utils.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/uniProject")
public class Controller {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private StudentEnrollCourseService studentEnrollCourseService;

    @PostMapping(value = "/registerUser")
    public Response registerUser(@RequestBody Users user) throws RoleInvalidException {
        return userService.registerUser(user);
    }
}
