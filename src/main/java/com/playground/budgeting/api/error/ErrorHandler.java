package com.playground.budgeting.api.error;

import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Component
public class ErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public GraphQLError handleInternalServerError(final RuntimeException runtimeException) {
        log.debug("Error caught: {}", runtimeException.getMessage());
        return GraphQLError.newError()
            .message("Internal Server Error")
            .errorType(ErrorType.INTERNAL_ERROR)
            .build();
    }
}