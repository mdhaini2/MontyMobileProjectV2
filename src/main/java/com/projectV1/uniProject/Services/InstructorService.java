package com.projectV1.uniProject.Services;

import com.projectV1.uniProject.Entities.Instructor;
import com.projectV1.uniProject.Entities.Users;
import com.projectV1.uniProject.Exceptions.InstructorExistsException;
import com.projectV1.uniProject.Exceptions.UserNotFoundException;
import com.projectV1.uniProject.Repositories.InstructorRepository;
import com.projectV1.uniProject.Repositories.UserRepository;
import com.projectV1.uniProject.Utils.Response;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    UserRepository userRepository;

    public Response addInstructor(Instructor instructor, int id) throws UserNotFoundException, InstructorExistsException {
        Users user;
        try {
            user = userRepository.findById(id).get();
        } catch (Exception e) {
            throw new UserNotFoundException("addInstructor: User with id: " + id + " not found");
        }

        Instructor instructor1 = instructorRepository.findByUserId(id);
        if (instructor1 != null) {
            throw new InstructorExistsException("Instructor with user id: " + id + " already exist");
        }
        instructor.setUser(user);
        instructorRepository.save(instructor);
        Response response = new Response(200, "Instructed Added Successfully", instructor);
        return response;
    }

    public Response deleteInstructor(int id) throws UserNotFoundException {
        Instructor instructor;

        try {
            instructor = instructorRepository.findById(id).get();
        } catch (Exception e) {
            throw new UserNotFoundException("deleteInstructor: User with id: " + id + " not found");

        }

        instructorRepository.deleteById(id);
        Response response = new Response(200, "Instructor delete successfully", null);
        return response;

    }

    public Response getAllInstructor() {
        List<Instructor> instructorList = instructorRepository.findAll();
        Response response = new Response(200, "All Instructors retrieved", instructorList);
        return response;
    }
}
