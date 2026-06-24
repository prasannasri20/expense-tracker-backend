package com.expense_tracker.Home.ExpenseTracker.Controller;

import com.expense_tracker.Home.ExpenseTracker.model.User;
import com.expense_tracker.Home.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
      @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User savedUser=userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }
}
