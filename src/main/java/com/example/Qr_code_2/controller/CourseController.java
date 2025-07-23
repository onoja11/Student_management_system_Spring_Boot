package com.example.Qr_code_2.controller;

import com.example.Qr_code_2.model.Courses;
import com.example.Qr_code_2.model.User;
import com.example.Qr_code_2.repository.CourseRepository;
import com.example.Qr_code_2.repository.UserRepository;
import com.example.Qr_code_2.service.CourseService;
import com.example.Qr_code_2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    public String allCourses(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Principal principal){
        String email = principal.getName();
        User user = userService.findByEmail(email);
        Page<Courses> coursesPage = courseService.getPagination(page, size);
        model.addAttribute("authUser",user);
        model.addAttribute("courses",coursesPage);
        return "course/index";
    }

    @GetMapping("/create")
    public String createCourses(Model model, Principal principal){
    String email = principal.getName();
    User user = userService.findByEmail(email);
        model.addAttribute("authUser",user);
        model.addAttribute("course", new Courses());
        return "course/create";
    }
    @PostMapping("/create")
    public String edit(@Valid @ModelAttribute("course") Courses courses, BindingResult result){
        if (result.hasErrors()){
            return "course/create";
        }
        courseRepository.save(courses);
        return "redirect:/course/";
    }

    @GetMapping("/reg")
    public String regCourses(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("authUser",user);
        model.addAttribute("courses", courseService.getAllCourse());

        return "course/reg";
    }


    @PostMapping("/reg")
    public String create( @RequestParam("selectedCourses") List<Integer> courseIds, Principal principal){
        String email = principal.getName();
        User user = userService.findByEmail(email);
        Long userId = user.getId();
        courseService.saveCourses(courseIds, userId);
        return "redirect:/course/reg";
    }
    @GetMapping("/deleteCourse/{id}")
    public String deleteStudent(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return "redirect:/course/";
    }
}
