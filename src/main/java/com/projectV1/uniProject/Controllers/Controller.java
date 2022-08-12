package com.projectV1.uniProject.Controllers;

import com.projectV1.uniProject.Entities.Users;
import com.projectV1.uniProject.Exceptions.RoleInvalidException;
import com.projectV1.uniProject.Exceptions.UserNotFoundException;
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

    @GetMapping(value = "/loginUser")
    public Object loginUser(@RequestParam String username, @RequestParam String password) throws RoleInvalidException {
        return userService.authenticateUser(username, password);
    }

    @PutMapping(value = "/updateUser")
    public Response updateUser(@RequestParam int id, @RequestParam String username, @RequestParam String password) throws UserNotFoundException {
        return userService.updateUser(id, username, password);
    }

    @GetMapping(value = "/getAllUsers")
    public Response getAllUser() {
        return userService.getAllUsers();
    }


}
