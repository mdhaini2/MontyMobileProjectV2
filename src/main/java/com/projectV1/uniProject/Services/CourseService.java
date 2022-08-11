package com.projectV1.uniProject.Services;

import com.projectV1.uniProject.Entities.Course;
import com.projectV1.uniProject.Repositories.CourseRepository;
import com.projectV1.uniProject.Repositories.InstructorRepository;
import com.projectV1.uniProject.Utils.Response;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    InstructorRepository instructorRepository;

    public Response addCourse(Course course, int instructor_id) {



        courseRepository.save(course);
        Response response = new Response(200,"Course Added Successfully",course);
        return  response;
    }
}
