package com.projectV1.uniProject.Controllers;

import com.projectV1.uniProject.Entities.Instructor;
import com.projectV1.uniProject.Exceptions.UserNotFoundException;
import com.projectV1.uniProject.Services.InstructorService;
import com.projectV1.uniProject.Utils.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    // Only instructor can delete Instructor
    @PostMapping(value = "/addInstructor/{user_id}")
    public Response addInstructor(@RequestBody Instructor instructor, @PathVariable int user_id) throws UserNotFoundException {
        return instructorService.addInstructor(instructor,user_id);
    }
}
