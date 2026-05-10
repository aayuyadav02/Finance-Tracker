package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.model.Expense;
import com.example.finance_tracker.service.ExpenseAnalysisService;
import com.example.finance_tracker.service.ExpenseService;
import com.example.finance_tracker.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseAnalysisServiceImpl implements ExpenseAnalysisService {

    private final ExpenseService expenseService;
    private final AuthenticationService authService;

    public ExpenseAnalysisServiceImpl(ExpenseService expenseService, AuthenticationService authService) {
        this.expenseService = expenseService;
        this.authService = authService;
    }

    @Override
    public Map<String, Double> getExpensesByCategory() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }

    @Override
    public List<String> getTopSpendingCategories(int limit) {
        Map<String, Double> expensesByCategory = getExpensesByCategory();
        return expensesByCategory.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, String> generateExpenseMinimizationSuggestions() {
        Map<String, Double> expensesByCategory = getExpensesByCategory();
        Map<String, String> suggestions = new HashMap<>();
        
        double totalExpense = expensesByCategory.values().stream().mapToDouble(Double::doubleValue).sum();
        
        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();
            double percentage = (amount / totalExpense) * 100;
            
            suggestions.put(category, generateSuggestionForCategory(category, amount, percentage));
        }
        
        return suggestions;
    }

    @Override
    public double getTotalExpenseByCategory(String category) {
        return getExpensesByCategory().getOrDefault(category, 0.0);
    }

    private String generateSuggestionForCategory(String category, double amount, double percentage) {
        if (percentage > 30) {
            return "Your " + category.toLowerCase() + " spending is high (" + String.format("%.1f", percentage) + "% of total expenses). Consider setting a monthly budget and tracking each purchase.";
        } else if (percentage > 20) {
            return "Your " + category.toLowerCase() + " expenses are significant (" + String.format("%.1f", percentage) + "%). Look for cheaper alternatives or reduce frequency.";
        } else if (percentage > 10) {
            return "Your " + category.toLowerCase() + " spending is moderate (" + String.format("%.1f", percentage) + "%). Small reductions could add up to significant savings.";
        } else {
            return "Your " + category.toLowerCase() + " expenses are well-controlled (" + String.format("%.1f", percentage) + "%). Keep up the good work!";
        }
    }
}
