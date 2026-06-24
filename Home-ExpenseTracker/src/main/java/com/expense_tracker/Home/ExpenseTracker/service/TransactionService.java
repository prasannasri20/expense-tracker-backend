package com.expense_tracker.Home.ExpenseTracker.service;

import com.expense_tracker.Home.ExpenseTracker.Repo.TransactionRepository;
import com.expense_tracker.Home.ExpenseTracker.Repo.UserRepository;
import com.expense_tracker.Home.ExpenseTracker.model.Transaction;
import com.expense_tracker.Home.ExpenseTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


//Add & get & delete transactions

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Transaction> getAllTransactions() {
        User loggedInUser = getLoggedInUser();
        return transactionRepository.findByUser(loggedInUser);
    }

    public Transaction getTransactionById(int id){
        return transactionRepository.findById(id).get();
    }

    public Transaction addOrUpdateTransaction(Transaction transaction) {
        User loggedInUser = getLoggedInUser();

        System.out.println("Logged in user id: " + loggedInUser.getId());
        System.out.println("Logged in user email: " + loggedInUser.getEmail());
        transaction.setUser(loggedInUser);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }

    private User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
