package com.playground.budgeting.dto;

import java.math.BigDecimal;

public record SavingsGoalInput (
    String name,
    BigDecimal targetAmount,
    BigDecimal savedAmount,
    String deadline,
    Long ownerId
) {
}
