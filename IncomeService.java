package com.example.finance_tracker.service;

import com.example.finance_tracker.model.Income;
import java.util.List;

public interface IncomeService {
    Income addIncome(Income income);
    List<Income> getAllIncome();
    void deleteIncome(Long id); 
}


