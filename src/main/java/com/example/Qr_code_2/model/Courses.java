package com.example.Qr_code_2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(unique = true)
    @NotBlank(message = "Field is Required")
    private String courseTitle;
//    @Column(unique = true)
    @NotBlank(message = "Field is Required")
    private String courseCode;

    public Courses(int id, String courseTitle, String courseCode) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
    }
public Courses(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
