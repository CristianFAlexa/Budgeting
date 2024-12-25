package com.playground.budgeting.repository;

import com.playground.budgeting.entity.SavingsGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Long> {
}
