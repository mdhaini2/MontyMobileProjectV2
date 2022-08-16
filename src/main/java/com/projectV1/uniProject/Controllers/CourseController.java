package com.projectV1.uniProject.Controllers;

import com.projectV1.uniProject.Entities.Course;
import com.projectV1.uniProject.Exceptions.*;
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
    public Response addCourse(@RequestBody Course course, @PathVariable int instructor_id) {
        return courseService.addCourse(course, instructor_id);
    }

    @PostMapping(value = "/enrollCourse")
    public Response enrollCourse(@RequestParam int student_id, @RequestParam int course_id) throws CourseEnrollExistsException {
        return courseService.enrollCourse(student_id, course_id);
    }

    @PutMapping(value = "/instructorUpdateStudentGrade")
    public Response instructorUpdateStudentGrade(@RequestParam int instructorId,
                                                 @RequestParam int studentEnrollId,
                                                 @RequestParam String grade) throws UserNotFoundException, StudentEnrollException, InstructorNotFoundException, GradeNotValidException, InstructorCourseException {
        return courseService.InstructorUpdateStudentGrade(instructorId, studentEnrollId, grade);
    }

    @DeleteMapping(value = "/deleteCourse")
    public Response deleteCourse(@RequestParam int courseId) throws DeleteCourseException, CourseNotFoundException {
        return courseService.deleteCourse(courseId);
    }

    @GetMapping(value = "/getAllCourses")
    public Response getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping(value = "/getAllStudentEnrolled")
    public Response getAllStudentEnrolled(@RequestParam int courseId){
        return courseService.getAllStudentEnrolled(courseId);
    }

}
