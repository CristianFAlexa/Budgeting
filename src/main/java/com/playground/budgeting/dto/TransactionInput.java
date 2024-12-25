package com.playground.budgeting.dto;

import java.math.BigDecimal;

public record TransactionInput(
    String cashFlow,
    BigDecimal amount,
    String category,
    String description,
    String initiatedOn,
    Long ownerId
) {
}
