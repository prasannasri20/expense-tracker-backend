package com.expense_tracker.Home.ExpenseTracker.service;

import com.expense_tracker.Home.ExpenseTracker.Repo.UserRepository;
import com.expense_tracker.Home.ExpenseTracker.dto.RegisterRequest;
import com.expense_tracker.Home.ExpenseTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) {
          return userRepository.save(user);

    }

    public User registerUser(RegisterRequest request) {

        User user =new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }
}
