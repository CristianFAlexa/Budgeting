package com.playground.budgeting.dto;

public record OwnerInput(
    String name,
    String email,
    String currency
) {
}
