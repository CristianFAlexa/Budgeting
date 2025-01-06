package com.playground.budgeting.api;

import com.playground.budgeting.dto.TransactionInput;
import com.playground.budgeting.entity.Transaction;
import com.playground.budgeting.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TransactionApi {

    private final TransactionService transactionService;

    @MutationMapping
    Transaction addTransaction(@Argument TransactionInput transaction) {
        return transactionService.addTransaction(transaction);
    }
}
