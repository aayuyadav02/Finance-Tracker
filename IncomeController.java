package com.example.finance_tracker.controller;

import com.example.finance_tracker.model.Income;
import com.example.finance_tracker.service.IncomeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
@RequestMapping("/income")
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    @ResponseBody
    public Income addIncome(@RequestBody Income income) {
        return incomeService.addIncome(income);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Income> getAllIncome() {
        return incomeService.getAllIncome();
    }

    @GetMapping("/delete/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return "redirect:/transactions";
    }


    @PostMapping("/add")
    public String addIncomeFromForm(@RequestParam double amount,
                                @RequestParam String source) {

        Income income = new Income();
        income.setAmount(amount);
        income.setSource(source);

        incomeService.addIncome(income);

        return "redirect:/dashboard";
    }
}
