package com.playground.budgeting.api;

import com.playground.budgeting.dto.TransactionInput;
import com.playground.budgeting.entity.Owner;
import com.playground.budgeting.entity.Transaction;
import com.playground.budgeting.entity.type.CashFlowType;
import com.playground.budgeting.repository.OwnerRepository;
import com.playground.budgeting.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@RequiredArgsConstructor
@Controller
public class OwnerApi {

    private final OwnerRepository ownerRepository;
    private final TransactionRepository transactionRepository;

    @QueryMapping
    Iterable<Owner> owners() {
        return ownerRepository.findAll();
    }

    @QueryMapping
    Owner ownerById(@Argument Long id) {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Owner with id: " + id + " not found"));
    }

    @MutationMapping
    Transaction addTransaction(@Argument TransactionInput transaction) {
        final var owner = ownerRepository.findById(transaction.ownerId())
            .orElseThrow(() -> new IllegalArgumentException("Owner with id: " + transaction.ownerId() + " not found"));

        var newTransaction = new Transaction();
        newTransaction.setCashFlow(CashFlowType.valueOf(transaction.cashFlow()));
        newTransaction.setAmount(transaction.amount());
        newTransaction.setCategory(transaction.category());
        newTransaction.setDescription(transaction.description());
        newTransaction.setInitiatedOn(Instant.parse(transaction.initiatedOn()));
        newTransaction.setOwner(owner);

        return transactionRepository.save(newTransaction);
    }
}
