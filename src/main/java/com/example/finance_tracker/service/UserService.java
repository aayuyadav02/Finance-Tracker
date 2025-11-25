package com.example.finance_tracker.service;

import com.example.finance_tracker.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
}
