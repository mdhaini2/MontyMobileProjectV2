package com.projectV1.uniProject.Services;

import com.projectV1.uniProject.Entities.*;
import com.projectV1.uniProject.Exceptions.*;
import com.projectV1.uniProject.Producer.Producer;
import com.projectV1.uniProject.Repositories.CourseRepository;
import com.projectV1.uniProject.Repositories.InstructorRepository;
import com.projectV1.uniProject.Repositories.StudentEnrollCourseRepository;
import com.projectV1.uniProject.Repositories.StudentRepository;
import com.projectV1.uniProject.Utils.Response;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
@AllArgsConstructor
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private Producer producer;
    @Autowired
    StudentEnrollCourseRepository studentEnrollCourseRepository;

    public Response addCourse(Course course, int instructor_id) {
        Instructor instructor = instructorRepository.findById(instructor_id).get();

        course.setInstructor(instructor);

        courseRepository.save(course);
        Response response = new Response(HttpStatus.OK.value(), "Course Added Successfully", course);
        return response;
    }

    public Response enrollCourse(int student_id, int course_id) throws CourseEnrollExistsException {

        Course course = courseRepository.findById(course_id).get();
        Student student = studentRepository.findById(student_id).get();

        Set<StudentEnrollCourse> enrollCourses = student.getEnrolledCourses();
        for(StudentEnrollCourse studentEnrollCourse:enrollCourses){
            if(studentEnrollCourse.getCourse().getId() == course_id){
                throw new CourseEnrollExistsException("Student already enrolled to the course");
            }
        }


        StudentEnrollCourse studentEnrollCourse = new StudentEnrollCourse("I", "Enrolled", student, course);



        studentEnrollCourseRepository.save(studentEnrollCourse);

        Set<StudentEnrollCourse> studentEnrollCourseSet = course.getStudentEnrolled();
        studentEnrollCourseSet.add(studentEnrollCourse);

        course.setStudentEnrolled(studentEnrollCourseSet);

        Set<StudentEnrollCourse> studentEnrollCourseSet1 = student.getEnrolledCourses();
        studentEnrollCourseSet1.add(studentEnrollCourse);
        student.setEnrolledCourses(studentEnrollCourseSet1);

        studentRepository.save(student);
        courseRepository.save(course);
        String description =student.getFullName()+ " ";
        for(StudentEnrollCourse studentEnrollCourse1: student.getEnrolledCourses())
            description += description(studentEnrollCourse1) + ",";
        log.info(description);
        Response response = new Response(200, "Student enrolled successfully!", description);

        return response;

    }

    public String description(StudentEnrollCourse studentEnrollCourse){
        String courseName = studentEnrollCourse.getCourse().getName();
        String courseStatus = studentEnrollCourse.getStatus();
        String description = courseStatus+ " course "+ courseName;
        return description;
    }

    public Response InstructorUpdateStudentGrade(int instructorId, int studentEnrollId, String grade) throws UserNotFoundException, InstructorNotFoundException, StudentEnrollException, InstructorCourseException, GradeNotValidException {
        Optional<Instructor> oInstructor = instructorRepository.findById(instructorId);
        if (oInstructor == null) {
            throw new InstructorNotFoundException("Instructor not found");
        }
        Instructor instructor = oInstructor.get();

        Optional<StudentEnrollCourse> oStudentEnroll = studentEnrollCourseRepository.findById(studentEnrollId);
        if (oStudentEnroll == null) {
            throw new StudentEnrollException("Student enrollment not found");
        }
        StudentEnrollCourse studentEnrollCourse = oStudentEnroll.get();

        if (studentEnrollCourse.getCourse().getInstructor().getId() != instructor.getId()) {
            throw new InstructorCourseException("Instructor can't update course grade");
        }

        Set<StudentEnrollCourse> courseList = studentEnrollCourse.getStudent().getEnrolledCourses();

        studentEnrollCourse.setStudentGrade(grade);
        if (grade.equalsIgnoreCase("F")) {
            studentEnrollCourse.setStatus("Failed");
        } else {
            studentEnrollCourse.setStatus("Completed");
        }

        studentEnrollCourseRepository.save(studentEnrollCourse);
        int gradedCourses = 0;
        double grades = 0;
        for (StudentEnrollCourse enrollment : courseList) {

            if (!enrollment.getStudentGrade().equalsIgnoreCase("I")) {
                gradedCourses++;
                grades += getGpaPoint(enrollment.getStudentGrade());
            }
        }

        double newGpa = grades /gradedCourses;

        Student student = studentEnrollCourse.getStudent();
        student.setGpa(newGpa);
        studentRepository.save(student);
        TransferStudentGrade transferStudentGrade = new TransferStudentGrade(
                studentEnrollCourse.getCourse().getName(),
                student.getFullName(),
                studentEnrollCourse.getStudentGrade(),
                studentEnrollCourse.getStatus());
        producer.sendJsonMessage(transferStudentGrade);
        Response response = new Response(200, "Course grade updated successfully", studentEnrollCourse);
        return response;

    }

    public Response deleteCourse(int courseId) throws CourseNotFoundException, DeleteCourseException {
        Optional<Course> oCourse = courseRepository.findById(courseId);
        if(oCourse == null){
            throw new CourseNotFoundException("Course with id: "+courseId+" not found");
        }
        Course course = oCourse.get();

        Set<StudentEnrollCourse> studentEnrollCourseSet = course.getStudentEnrolled();

        for(StudentEnrollCourse studentEnrollCourse: studentEnrollCourseSet){
            if(studentEnrollCourse.getStatus().equalsIgnoreCase("Enrolled")){
                throw new DeleteCourseException("Can't delete course if student still enrolled");
            }
        }

        courseRepository.delete(course);

        Response response = new Response(200,"Course deleted successfully!",null);
        return response;

    }

    public double getGpaPoint(String grade) throws GradeNotValidException {
        double points = 0;

        switch (grade) {
            case "A":
                points = 4;
                break;
            case "B":
                points = 3;
                break;
            case "C":
                points = 2;
                break;
            case "D":
                points = 1;
                break;
            case "F":
                points = 0;
                break;
            default:
                throw new GradeNotValidException(grade + " is not a valid grade");
        }
        return points;
    }

    public Response getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        Response response = new Response(200,"All courses retrieved successfully",courseList);
        return response;
    }

    public Response getAllStudentEnrolled(int courseId) {
        Course course = courseRepository.findById(courseId).get();
        Set<StudentEnrollCourse> enrollCourseSet = course.getStudentEnrolled();

        Response response = new Response(200,"Students enrolled in course "+course.getName()+ " retrieved successfully",enrollCourseSet);
        return response;
    }
}
