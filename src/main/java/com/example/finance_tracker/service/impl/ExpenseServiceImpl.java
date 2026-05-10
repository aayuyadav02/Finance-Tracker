package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.model.Expense;
import com.example.finance_tracker.model.User;
import com.example.finance_tracker.repository.ExpenseRepository;
import com.example.finance_tracker.service.ExpenseService;
import com.example.finance_tracker.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    private final ExpenseRepository expenseRepository;
    private final AuthenticationService authService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, AuthenticationService authService){
        this.expenseRepository = expenseRepository;
        this.authService = authService;
    }

    @Override
    public Expense addExpense(Expense expense){
        User currentUser = authService.getCurrentUser();
        if (currentUser != null) {
            expense.setUser(currentUser);
        }
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses(){
        User currentUser = authService.getCurrentUser();
        if (currentUser != null) {
            return expenseRepository.findByUser(currentUser);
        }
        return List.of();
    }

    @Override
    public void deleteExpense(Long id) {
        User currentUser = authService.getCurrentUser();
        if (currentUser != null) {
            Expense expense = expenseRepository.findById(id).orElse(null);
            if (expense != null && expense.getUser().getId().equals(currentUser.getId())) {
                expenseRepository.deleteById(id);
            }
        }
    }

}
