package com.playground.budgeting.dto;

import java.math.BigDecimal;

public record BudgetInput(
    String category,
    BigDecimal threshold,
    BigDecimal spent,
    Long ownerId
) {
}
