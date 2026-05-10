package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.model.Income;
import com.example.finance_tracker.model.User;
import com.example.finance_tracker.repository.IncomeRepository;
import com.example.finance_tracker.service.IncomeService;
import com.example.finance_tracker.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{
    private final IncomeRepository incomeRepository;
    private final AuthenticationService authService;

    public IncomeServiceImpl(IncomeRepository incomeRepository, AuthenticationService authService){
        this.incomeRepository = incomeRepository;
        this.authService = authService;
    }

    @Override
    public Income addIncome(Income income){
        User currentUser = authService.getCurrentUser();
        if (currentUser != null) {
            income.setUser(currentUser);
        }
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> getAllIncome(){
        User currentUser = authService.getCurrentUser();
        if (currentUser != null) {
            return incomeRepository.findByUser(currentUser);
        }
        return List.of();
    }

    @Override
    public void deleteIncome(Long id) {
        User currentUser = authService.getCurrentUser();
        if (currentUser != null) {
            Income income = incomeRepository.findById(id).orElse(null);
            if (income != null && income.getUser().getId().equals(currentUser.getId())) {
                incomeRepository.deleteById(id);
            }
        }
    }
}
