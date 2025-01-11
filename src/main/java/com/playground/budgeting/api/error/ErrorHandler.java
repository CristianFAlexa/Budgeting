package com.playground.budgeting.api.error;

import graphql.GraphQLError;
import graphql.GraphQLException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @GraphQlExceptionHandler({GraphQLException.class, ConstraintViolationException.class})
    public GraphQLError handleInternalServerError(final Exception exception) {
        log.debug("Error caught: {}", exception.getMessage());
        return GraphQLError.newError()
            .message("Internal Server Error")
            .errorType(ErrorType.INTERNAL_ERROR)
            .build();
    }

    @GraphQlExceptionHandler(AccessDeniedException.class)
    public GraphQLError handleAccessDenied(final Exception exception) {
        log.debug("Access denied: {}", exception.getMessage());
        return GraphQLError.newError()
            .message("Access Denied")
            .errorType(ErrorType.FORBIDDEN)
            .build();
    }
}