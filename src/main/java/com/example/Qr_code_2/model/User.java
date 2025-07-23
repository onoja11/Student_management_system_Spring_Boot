package com.example.Qr_code_2.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JoinColumnsOrFormulas;

import java.util.ArrayList;
import java.util.List;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID
    private Long id;

@NotBlank(message = "Username is required")
    private String username;
    @Column(unique = true)
    @NotBlank(message = "Field is Required")
    private String matric_no;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotBlank(message = "Field is Required")
    private Role role = Role.STUDENT;
    @Nullable
    private String profilePicture;
    @Column(unique = true)
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Field is Required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_courses",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Courses> selectedCourses = new ArrayList<>();

public User(){}
    public User(Long id, String username, String matric_no, Role role, @Nullable String profilePicture, String email, String password, List<Courses> selectedCourses) {
        this.id = id;
        this.username = username;
        this.matric_no = matric_no;
        this.role = role;
        this.profilePicture = profilePicture;
        this.email = email;
        this.password = password;
        this.selectedCourses = selectedCourses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatric_no() {
        return matric_no;
    }

    public void setMatric_no(String matric_no) {
        this.matric_no = matric_no;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Nullable
    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(@Nullable String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Courses> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<Courses> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }
}
