package com.playground.budgeting.service;

import com.playground.budgeting.dto.TransactionInput;
import com.playground.budgeting.entity.Transaction;
import com.playground.budgeting.entity.type.CashFlowType;
import com.playground.budgeting.repository.OwnerRepository;
import com.playground.budgeting.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OwnerRepository ownerRepository;

    public Transaction addTransaction(TransactionInput transaction) {
        final var owner = ownerRepository.findById(transaction.ownerId())
            .orElseThrow(() -> new IllegalArgumentException("Owner with id: " + transaction.ownerId() + " not found"));

        var newTransaction = Transaction.builder()
            .cashFlow(CashFlowType.valueOf(transaction.cashFlow()))
            .amount(transaction.amount())
            .category(transaction.category())
            .description(transaction.description())
            .initiatedOn(Instant.parse(transaction.initiatedOn()))
            .owner(owner)
            .build();

        return transactionRepository.save(newTransaction);
    }
}
