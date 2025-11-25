package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.model.Expense;
import com.example.finance_tracker.repository.ExpenseRepository;
import com.example.finance_tracker.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);   // ✅ Correct place
    }

}
