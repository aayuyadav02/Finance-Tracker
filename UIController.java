package com.example.finance_tracker.controller;

import com.example.finance_tracker.model.Income;
import com.example.finance_tracker.service.ExpenseService;
import com.example.finance_tracker.service.IncomeService;
import com.example.finance_tracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UIController {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final UserService userService;

    public UIController(ExpenseService expenseService, IncomeService incomeService, UserService userService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/dashboard";
    }


    // Dashboard Page
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        double totalIncome = incomeService.getAllIncome()
                .stream().mapToDouble(i -> i.getAmount()).sum();

        double totalExpense = expenseService.getAllExpenses()
                .stream().mapToDouble(e -> e.getAmount()).sum();

        double balance = totalIncome - totalExpense;

        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("balance", balance);

        model.addAttribute("content", "dashboard.html");
        return "layout";
    }

    // Add Income Page
    @GetMapping("/add-income")
    public String addIncome(Model model) {
        model.addAttribute("content", "add-income.html");
        return "layout";
    }

    // Add Expense Page
    @GetMapping("/add-expense")
    public String addExpense(Model model) {
        model.addAttribute("content", "add-expense.html");
        return "layout";
    }

    // Transactions Page
    @GetMapping("/transactions")
    public String transactions(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        model.addAttribute("income", incomeService.getAllIncome());

        model.addAttribute("content", "transactions.html");
        return "layout";
    }

    

}
