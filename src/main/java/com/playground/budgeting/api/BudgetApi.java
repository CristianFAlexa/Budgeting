package com.playground.budgeting.api;

import com.playground.budgeting.dto.BudgetInput;
import com.playground.budgeting.entity.Budget;
import com.playground.budgeting.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BudgetApi {

    private final BudgetService budgetService;

    @MutationMapping
    public Budget addBudget(@Argument BudgetInput budget) {
        return budgetService.addBudget(budget);
    }
}
