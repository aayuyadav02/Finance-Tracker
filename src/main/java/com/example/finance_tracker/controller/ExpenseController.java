package com.example.finance_tracker.controller;

import com.example.finance_tracker.model.Expense;
import com.example.finance_tracker.service.ExpenseService;
import com.example.finance_tracker.service.ExpenseAnalysisService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseAnalysisService expenseAnalysisService;

    public ExpenseController(ExpenseService expenseService, ExpenseAnalysisService expenseAnalysisService) {
        this.expenseService = expenseService;
        this.expenseAnalysisService = expenseAnalysisService;
    }

    @PostMapping
    @ResponseBody
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/delete/{id}")
        public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/transactions";
    }


    @PostMapping("/add")
    public String addExpenseFromForm(@RequestParam double amount,
                                 @RequestParam String category) {

        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setCategory(category);

        expenseService.addExpense(expense);

        return "redirect:/dashboard";
    }

    @GetMapping("/suggestions")
    @ResponseBody
    public Map<String, String> getExpenseSuggestions() {
        return expenseAnalysisService.generateExpenseMinimizationSuggestions();
    }

}
