package com.example.finance_tracker.controller;

import com.example.finance_tracker.model.User;
import com.example.finance_tracker.service.UserService;
import com.example.finance_tracker.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authService;

    public UserController(UserService userService, AuthenticationService authService){
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("content", "login.html");
        return "layout";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, 
                       RedirectAttributes redirectAttributes) {
        try {
            User user = authService.login(email, password);
            redirectAttributes.addFlashAttribute("message", "Welcome back, " + user.getName() + "!");
            return "redirect:/dashboard";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("content", "register.html");
        return "layout";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email, 
                          @RequestParam String password, RedirectAttributes redirectAttributes) {
        try {
            User user = authService.register(name, email, password);
            redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
            return "redirect:/auth/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/register";
        }
    }

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        authService.logout();
        redirectAttributes.addFlashAttribute("message", "You have been logged out.");
        return "redirect:/auth/login";
    }

    @GetMapping("/current")
    @ResponseBody
    public User getCurrentUser() {
        return authService.getCurrentUser();
    }
}
