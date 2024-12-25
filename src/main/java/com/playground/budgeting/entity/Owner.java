package com.playground.budgeting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Owner")
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String currency;
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Set<Budget> budgets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Set<SavingsGoal> savingsGoals = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id) && Objects.equals(name, owner.name) && Objects.equals(email, owner.email) && Objects.equals(currency, owner.currency) && Objects.equals(createdAt, owner.createdAt) && Objects.equals(budgets, owner.budgets) && Objects.equals(transactions, owner.transactions) && Objects.equals(savingsGoals, owner.savingsGoals);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(currency);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(budgets);
        result = 31 * result + Objects.hashCode(transactions);
        result = 31 * result + Objects.hashCode(savingsGoals);
        return result;
    }
}
