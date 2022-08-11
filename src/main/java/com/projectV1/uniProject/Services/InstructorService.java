package com.projectV1.uniProject.Services;

import com.projectV1.uniProject.Entities.Instructor;
import com.projectV1.uniProject.Entities.Users;
import com.projectV1.uniProject.Exceptions.UserNotFoundException;
import com.projectV1.uniProject.Repositories.InstructorRepository;
import com.projectV1.uniProject.Repositories.UserRepository;
import com.projectV1.uniProject.Utils.Response;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    UserRepository userRepository;

    public Response addInstructor(Instructor instructor, int id) throws UserNotFoundException {
        Users user = userRepository.findById(id).get();

        if (user == null) {
            throw new UserNotFoundException("addStudent: User with id: " + id + " not found");
        }
        instructor.setUser(user);
        instructorRepository.save(instructor);
        Response response = new Response(200, "Instructed Added Successfully", instructor);
        return response;
    }

}
