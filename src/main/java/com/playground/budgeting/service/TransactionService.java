package com.playground.budgeting.service;

import com.playground.budgeting.dto.TransactionInput;
import com.playground.budgeting.entity.Budget;
import com.playground.budgeting.entity.Transaction;
import com.playground.budgeting.entity.type.CashFlowType;
import com.playground.budgeting.repository.OwnerRepository;
import com.playground.budgeting.repository.TransactionRepository;
import com.playground.budgeting.subscription.BudgetPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

import static com.playground.budgeting.entity.type.CashFlowType.EXPENSE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OwnerRepository ownerRepository;
    private final BudgetPublisher budgetPublisher;

    @Transactional
    public Transaction addTransaction(TransactionInput transaction) {
        final var owner = ownerRepository.findById(transaction.ownerId())
            .orElseThrow(() -> new IllegalArgumentException("Owner with id: " + transaction.ownerId() + " not found"));

        Optional<Budget> existingBudget = Optional.empty();
        if (EXPENSE.equals(CashFlowType.valueOf(transaction.cashFlow()))) {
            existingBudget = owner.getBudgets().stream()
                .filter(budget -> budget.getCategory().equals(transaction.category()))
                .findFirst();
            existingBudget.ifPresent(budget -> {
                budget.addExpenditure(transaction.amount());
                budgetPublisher.publish(budget);
            });
        }

        var newTransaction = Transaction.builder()
            .cashFlow(CashFlowType.valueOf(transaction.cashFlow()))
            .amount(transaction.amount())
            .category(transaction.category())
            .description(transaction.description())
            .initiatedOn(Instant.parse(transaction.initiatedOn()))
            .budget(existingBudget.orElse(null))
            .owner(owner)
            .build();

        return transactionRepository.save(newTransaction);
    }
}
