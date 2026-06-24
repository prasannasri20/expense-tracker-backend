package com.expense_tracker.Home.ExpenseTracker.Controller;


import com.expense_tracker.Home.ExpenseTracker.dto.AuthResponse;
import com.expense_tracker.Home.ExpenseTracker.dto.LoginRequest;
import com.expense_tracker.Home.ExpenseTracker.dto.RegisterRequest;
import com.expense_tracker.Home.ExpenseTracker.model.User;
import com.expense_tracker.Home.ExpenseTracker.service.JwtService;
import com.expense_tracker.Home.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager  authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request){
        return userService.registerUser(request);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
         Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )

        );

        if(authentication.isAuthenticated()){
            String token=jwtService.generateToken(request.getEmail());
            return new AuthResponse(token, "Login Succesful");

        }
        else{
            return new AuthResponse(null,"login Failed");
        }
    }


}
