package com.expense_tracker.Home.ExpenseTracker.Repo;

import com.expense_tracker.Home.ExpenseTracker.model.Transaction;
import com.expense_tracker.Home.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{

    List<Transaction> findByUser(User user);

//   Purpose:
//    Save data
//    Fetch data
//    Delete data
//

}
