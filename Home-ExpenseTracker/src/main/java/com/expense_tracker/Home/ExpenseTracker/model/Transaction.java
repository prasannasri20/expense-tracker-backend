package com.expense_tracker.Home.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Entity(name = "Transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
     private String category;
     private Integer amount;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER  )
    @JoinColumn(name = "user_id")
     private User user;
}
