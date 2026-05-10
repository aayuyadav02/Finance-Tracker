package com.example.finance_tracker.service;

import java.util.List;
import java.util.Map;

public interface ExpenseAnalysisService {
    Map<String, Double> getExpensesByCategory();
    List<String> getTopSpendingCategories(int limit);
    Map<String, String> generateExpenseMinimizationSuggestions();
    double getTotalExpenseByCategory(String category);
}
