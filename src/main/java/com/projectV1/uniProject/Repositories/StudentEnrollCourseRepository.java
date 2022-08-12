package com.projectV1.uniProject.Repositories;

import com.projectV1.uniProject.Entities.Student;
import com.projectV1.uniProject.Entities.StudentEnrollCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEnrollCourseRepository  extends JpaRepository<StudentEnrollCourse, Integer> {
}
