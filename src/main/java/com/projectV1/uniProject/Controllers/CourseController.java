package com.projectV1.uniProject.Controllers;

import com.projectV1.uniProject.Entities.Course;
import com.projectV1.uniProject.Services.CourseService;
import com.projectV1.uniProject.Utils.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping(value = "/addCourse/{instructor_id}")
    public Response addCourse(@RequestBody Course course, @PathVariable int instructor_id){
        return courseService.addCourse(course,instructor_id);
    }
}
