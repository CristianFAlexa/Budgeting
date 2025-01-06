package com.playground.budgeting.api;

import com.playground.budgeting.dto.SavingsGoalInput;
import com.playground.budgeting.entity.SavingsGoal;
import com.playground.budgeting.service.SavingsGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class SavingsGoalApi {

    private final SavingsGoalService savingsGoalService;

    @MutationMapping
    public SavingsGoal addSavingsGoal(@Argument SavingsGoalInput savingsGoal) {
        return savingsGoalService.addSavingsGoal(savingsGoal);
    }
}
