package com.expense_tracker.Home.ExpenseTracker.dto;


import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
