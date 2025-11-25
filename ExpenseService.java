package com.example.finance_tracker.service;

import com.example.finance_tracker.model.Expense;
import java.util.List;

public interface ExpenseService {
    Expense addExpense(Expense expense);
    List<Expense> getAllExpenses();
    void deleteExpense(Long id);
}


