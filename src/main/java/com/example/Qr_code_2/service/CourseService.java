package com.example.Qr_code_2.service;

import com.example.Qr_code_2.model.Courses;
import com.example.Qr_code_2.model.User;
import com.example.Qr_code_2.repository.CourseRepository;
import com.example.Qr_code_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository repository;
    @Autowired
    UserRepository userRepository;

    public List<Courses> getAllCourse(){
        return repository.findAll();
    }

    public Courses getCourse(int id){
        return repository.findById(id).get();
    }
    public Courses findByCourseTitle(String username) {
        return repository.findByCourseTitle(username);
    }

    public Page<Courses> getPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Courses createcourse(Courses courses){
        return repository.save(courses);
    }

    public void saveCourses(List<Integer> courseIds, Long userId){
        User user = userRepository.findById(userId).get();
        List<Courses> courses = repository.findAllById(courseIds);
        user.setSelectedCourses(courses);
        userRepository.save(user);
    }
    public void deleteCourse(Integer id){
        repository.deleteById(id);
    }
}
