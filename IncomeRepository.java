package com.example.finance_tracker.repository;

import com.example.finance_tracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {

}
