package com.example.Qr_code_2.repository;

import com.example.Qr_code_2.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {
    Courses findByCourseTitle(String courseTitle); // For login lookup

}
