package com.projectV1.uniProject.Services;

import com.projectV1.uniProject.Repositories.CourseRepository;
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
}