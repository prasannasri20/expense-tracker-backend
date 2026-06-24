package com.expense_tracker.Home.ExpenseTracker.dto;


import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
