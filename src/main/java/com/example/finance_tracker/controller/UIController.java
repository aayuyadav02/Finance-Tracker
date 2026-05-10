package com.example.finance_tracker.controller;

import com.example.finance_tracker.model.Income;
import com.example.finance_tracker.service.ExpenseService;
import com.example.finance_tracker.service.IncomeService;
import com.example.finance_tracker.service.UserService;
import com.example.finance_tracker.service.AuthenticationService;
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
    private final AuthenticationService authService;

    public UIController(ExpenseService expenseService, IncomeService incomeService, UserService userService, AuthenticationService authService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/dashboard";
    }


    // Dashboard Page
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        if (authService.getCurrentUser() == null) {
            return "redirect:/auth/login";
        }

        double totalIncome = incomeService.getAllIncome()
                .stream().mapToDouble(i -> i.getAmount()).sum();

        double totalExpense = expenseService.getAllExpenses()
                .stream().mapToDouble(e -> e.getAmount()).sum();

        double balance = totalIncome - totalExpense;

        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("balance", balance);
        model.addAttribute("currentUser", authService.getCurrentUser());

        model.addAttribute("content", "dashboard.html");
        return "layout";
    }

    // Add Income Page
    @GetMapping("/add-income")
    public String addIncome(Model model) {
        if (authService.getCurrentUser() == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("currentUser", authService.getCurrentUser());
        model.addAttribute("content", "add-income.html");
        return "layout";
    }

    // Add Expense Page
    @GetMapping("/add-expense")
    public String addExpense(Model model) {
        if (authService.getCurrentUser() == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("currentUser", authService.getCurrentUser());
        model.addAttribute("content", "add-expense.html");
        return "layout";
    }

    // Transactions Page
    @GetMapping("/transactions")
    public String transactions(Model model) {
        if (authService.getCurrentUser() == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("expenses", expenseService.getAllExpenses());
        model.addAttribute("income", incomeService.getAllIncome());
        model.addAttribute("currentUser", authService.getCurrentUser());

        model.addAttribute("content", "transactions.html");
        return "layout";
    }

    // Suggestions Page
    @GetMapping("/suggestions")
    public String suggestions(Model model) {
        if (authService.getCurrentUser() == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("currentUser", authService.getCurrentUser());
        model.addAttribute("content", "suggestions.html");
        return "layout";
    }

    

}
