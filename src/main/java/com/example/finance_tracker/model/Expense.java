package com.example.finance_tracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user-id", referencedColumnName = "id")
    private User user;
}
