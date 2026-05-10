package com.example.finance_tracker.service;

import com.example.finance_tracker.model.User;

public interface AuthenticationService {
    User register(String name, String email, String password);
    User login(String email, String password);
    User getCurrentUser();
    void logout();
}
