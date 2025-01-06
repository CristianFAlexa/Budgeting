package com.playground.budgeting.entity;

import com.playground.budgeting.entity.type.CashFlowType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Transaction")
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CashFlowType cashFlow;
    private BigDecimal amount;
    private String category;
    private String description;
    private Instant initiatedOn;
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(cashFlow, that.cashFlow) && Objects.equals(amount, that.amount) && Objects.equals(category, that.category) && Objects.equals(description, that.description) && Objects.equals(initiatedOn, that.initiatedOn) && Objects.equals(createdAt, that.createdAt) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
