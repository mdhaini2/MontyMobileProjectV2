package com.projectV1.uniProject.Repositories;

import com.projectV1.uniProject.Entities.Course;
import com.projectV1.uniProject.Entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
