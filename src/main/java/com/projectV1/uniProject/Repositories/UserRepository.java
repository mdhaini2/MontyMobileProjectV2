package com.projectV1.uniProject.Repositories;

import com.projectV1.uniProject.Entities.Student;
import com.projectV1.uniProject.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users findByUsername(String username);
}
