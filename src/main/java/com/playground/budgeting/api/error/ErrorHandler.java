package com.playground.budgeting.api.error;

import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleInternalServerError(final Exception exception) {
        log.debug("Error caught: {}", exception.getMessage());
        return GraphQLError.newError()
            .message("Internal Server Error")
            .errorType(ErrorType.INTERNAL_ERROR)
            .build();
    }
}