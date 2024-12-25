package com.playground.budgeting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Budget")
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private BigDecimal threshold;
    private BigDecimal spent;
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Objects.equals(id, budget.id) && Objects.equals(category, budget.category) && Objects.equals(threshold, budget.threshold) && Objects.equals(spent, budget.spent) && Objects.equals(createdAt, budget.createdAt) && Objects.equals(transactions, budget.transactions) && Objects.equals(owner, budget.owner);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
