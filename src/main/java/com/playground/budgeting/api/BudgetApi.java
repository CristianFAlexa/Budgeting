package com.playground.budgeting.api;

import com.playground.budgeting.dto.BudgetInput;
import com.playground.budgeting.entity.Budget;
import com.playground.budgeting.service.BudgetService;
import com.playground.budgeting.subscription.BudgetPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class BudgetApi {

    private final BudgetService budgetService;
    private final BudgetPublisher budgetPublisher;

    @MutationMapping
    public Budget addBudget(@Argument BudgetInput budget) {
        return budgetService.addBudget(budget);
    }

    @SubscriptionMapping
    public Flux<Budget> budgetPublishedFor(@Argument Long ownerId) {
        return budgetPublisher.getBudgetPublisherFor(ownerId);
    }
}
