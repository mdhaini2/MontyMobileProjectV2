package com.projectV1.uniProject.Repositories;

import com.projectV1.uniProject.Entities.Instructor;
import com.projectV1.uniProject.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository  extends JpaRepository<Instructor, Integer> {
}
