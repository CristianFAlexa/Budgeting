package com.playground.budgeting.service;

import com.playground.budgeting.dto.BudgetInput;
import com.playground.budgeting.entity.Budget;
import com.playground.budgeting.repository.BudgetRepository;
import com.playground.budgeting.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public Budget addBudget(BudgetInput budget) {
        final var owner = ownerRepository.findById(budget.ownerId())
            .orElseThrow(() -> new IllegalArgumentException("Owner with id: " + budget.ownerId() + " not found"));

        final var newBudget = Budget.builder()
            .owner(owner)
            .category(budget.category())
            .threshold(budget.threshold())
            .spent(budget.spent())
            .build();

        return budgetRepository.save(newBudget);
    }
}
