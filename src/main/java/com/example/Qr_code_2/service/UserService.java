package com.example.Qr_code_2.service;

import com.example.Qr_code_2.model.User;
import com.example.Qr_code_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        repo.save(user);
    }
    public Page<User> getPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }
    public User findByEmail(String username) {
        return repo.findByEmail(username);
    }
    public void deleteUser(Long id){
        repo.deleteById(id);
    }
    public User getUser(Long id){
        return repo.findById(id).get();
    }


}
