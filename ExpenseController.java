package com.example.finance_tracker.controller;

import com.example.finance_tracker.model.Expense;
import com.example.finance_tracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
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

}
