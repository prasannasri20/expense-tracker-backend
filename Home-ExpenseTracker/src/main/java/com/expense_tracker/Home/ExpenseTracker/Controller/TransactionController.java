package com.expense_tracker.Home.ExpenseTracker.Controller;

import com.expense_tracker.Home.ExpenseTracker.Repo.UserRepository;
import com.expense_tracker.Home.ExpenseTracker.model.Transaction;
import com.expense_tracker.Home.ExpenseTracker.model.User;
import com.expense_tracker.Home.ExpenseTracker.service.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    //Create APIs here post & get & put &  delete here
@GetMapping("/transactions")
 public ResponseEntity<List<Transaction>> getTransactions(){
     return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
 }

@GetMapping("/transaction/{id}")
 public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
    Transaction transaction = transactionService.getTransactionById(id);
    if (transaction.getId() > 0) {
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
    else{
        return new ResponseEntity<>(transaction,HttpStatus.NOT_FOUND);
    }
}
    @PutMapping("/transaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable int id,
            @RequestBody Transaction transaction){

        Transaction existing = transactionService.getTransactionById(id);

        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        transaction.setId(id);

        Transaction updated = transactionService.addOrUpdateTransaction(transaction);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @PostMapping("/transaction")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction){
        Transaction savedTransaction = transactionService.addOrUpdateTransaction(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> deleteTransacton(@PathVariable int id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            transactionService.deleteTransaction(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
