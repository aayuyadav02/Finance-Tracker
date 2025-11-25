package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.model.Income;
import com.example.finance_tracker.repository.IncomeRepository;
import com.example.finance_tracker.service.IncomeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{
    private final IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository){
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Income addIncome(Income income){
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> getAllIncome(){
        return incomeRepository.findAll();
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);   
    }
}
