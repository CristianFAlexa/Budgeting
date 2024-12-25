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
@Entity(name = "SavingsGoal")
@Table(name = "savingsGoal")
public class SavingsGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal targetAmount;
    private BigDecimal savedAmount;
    private Instant deadline;
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
        SavingsGoal that = (SavingsGoal) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(targetAmount, that.targetAmount) && Objects.equals(savedAmount, that.savedAmount) && Objects.equals(deadline, that.deadline) && Objects.equals(createdAt, that.createdAt) && Objects.equals(transactions, that.transactions) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
