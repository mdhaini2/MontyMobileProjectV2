package com.projectV1.uniProject.Controllers;

import com.projectV1.uniProject.Entities.Instructor;
import com.projectV1.uniProject.Exceptions.InstructorExistsException;
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


    @PostMapping(value = "/addInstructor/{user_id}")
    public Response addInstructor(@RequestBody Instructor instructor, @PathVariable int user_id) throws UserNotFoundException, InstructorExistsException {
        return instructorService.addInstructor(instructor, user_id);
    }

    @DeleteMapping(value = "/deleteInstructor")
    public Response deleteInstructor(@RequestParam int id) throws UserNotFoundException {
        return instructorService.deleteInstructor(id);
    }

    @GetMapping(value = "/getAllInstructors")
    public Response getAllInstructor(){
        return instructorService.getAllInstructor();
    }

}
