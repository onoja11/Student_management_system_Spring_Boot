package com.example.Qr_code_2.controller;

import com.example.Qr_code_2.model.User;
import com.example.Qr_code_2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/students")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Model model,Principal principal){
        String email = principal.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("authUser",user);
        return "profile";
    }
@GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, Authentication authentication){
    userService.deleteUser(id);
    new SecurityContextLogoutHandler().logout(request,response,authentication);
    return "redirect:/home";
}

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/home";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }
    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/home";
    }
}
